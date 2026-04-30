package com.chatapp.client;

public interface ChatClient {
    void sendMessage(String message, String recipient);
    void receiveMessage(String message, String sender);
    String getUsername();
}