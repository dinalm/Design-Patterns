package com.chatapp.mediator;

import com.chatapp.client.ChatClient;

public interface ChatMediator {
    void registerClient(ChatClient client);
    void sendMessage(String message, ChatClient sender, String recipient);
}