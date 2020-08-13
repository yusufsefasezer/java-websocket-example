package com.yusufsezer;

import java.time.LocalDateTime;

public class Message {

    private String action;
    private String value;
    private String username;
    private LocalDateTime date;

    public Message() {
        this.date = LocalDateTime.now();
    }

    public Message(String action, String username, String value) {
        this.action = action;
        this.username = username;
        this.value = value;
        this.date = LocalDateTime.now();
    }

    public Message(String action, String value) {
        this.action = action;
        this.value = value;
        this.date = LocalDateTime.now();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{"
                + "action=" + action
                + ", value=" + value
                + ", username=" + username
                + ", date=" + date + '}';
    }

}
