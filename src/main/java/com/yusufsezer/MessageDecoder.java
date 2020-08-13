package com.yusufsezer;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String text) throws DecodeException {
        try {
            return JsonbBuilder
                    .create()
                    .fromJson(text, Message.class);
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
