package com.chatapp.mediator;

import com.chatapp.client.ChatClient;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomMediator implements ChatMediator {

    private final Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void registerClient(ChatClient client) {
        clients.put(client.getUsername(), client);
    }

    @Override
    public void sendMessage(String message, ChatClient sender, String recipient) {
        ChatClient recipientClient = clients.get(recipient);

        if (recipientClient != null) {
            recipientClient.receiveMessage(message, sender.getUsername());
            sender.receiveMessage(message, "You to " + recipient);
        } else {
            sender.receiveMessage("User not found.", "System");
        }
    }
}