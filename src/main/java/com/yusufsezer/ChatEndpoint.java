package com.yusufsezer;

import jakarta.websocket.CloseReason;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(
        value = "/chat",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class ChatEndpoint {

    @OnMessage
    public void onMessage(Session session, Message message) {
        if ("setUserName".equals(message.getAction())) {
            String username = message.getValue();
            setUserName(session, username);
        } else if ("sendMessage".equals(message.getAction())) {
            String msg = message.getValue();
            sendMessage(session, msg);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        closeSession(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        String username = getUserName(session);
        String size = String.valueOf(getUserSize(session));
        Message leftMessage = new Message("left", username, size);
        sendAllMessage(session, leftMessage);
    }

    private int getUserSize(Session session) {
        return session
                .getOpenSessions()
                .size();
    }

    private void setUserName(Session session, String username) {
        boolean isTaken = isTaken(session, username);
        if (isTaken) {
            closeSession(session);
            return;
        }
        session.getUserProperties().put("username", username);
        String size = String.valueOf(getUserSize(session));
        Message joinMessage = new Message("join", username, size);
        sendAllMessage(session, joinMessage);
    }

    private void sendMessage(Session session, String message) {
        String username = getUserName(session);
        Message newMessage = new Message("newMessage", username, message);
        sendAllMessage(session, newMessage);
    }

    private String getUserName(Session session) {
        return String.valueOf(session.getUserProperties().get("username"));
    }

    private boolean isTaken(Session session, String username) {
        return session
                .getOpenSessions()
                .stream()
                .anyMatch(p -> p.getUserProperties().containsValue(username));
    }

    private void sendAllMessage(Session session, Message message) {
        session
                .getOpenSessions()
                .stream()
                .filter(p -> p.isOpen() == true)
                .forEach((s) -> {
                    try {
                        s.getBasicRemote().sendObject(message);
                    } catch (IOException | EncodeException e) {
                        System.err.println(e);
                    }
                });
    }

    private void closeSession(Session session) {
        try {
            session.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
