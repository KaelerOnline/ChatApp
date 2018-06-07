package com.android.teaching.chatapp;

public class ChatMessage {
    private String text;
    private String username;

    public ChatMessage(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
