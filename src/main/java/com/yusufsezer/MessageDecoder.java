package com.yusufsezer;

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String text) throws DecodeException {
        try {
            return JsonbBuilder.create().fromJson(text, Message.class);
        } catch (JsonbException e) {
            throw new DecodeException(text, e.getMessage(), e);
        }
    }

    @Override
    public boolean willDecode(String text) {
        return true;
    }

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

}
