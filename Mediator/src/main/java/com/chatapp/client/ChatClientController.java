package com.chatapp.client;

import com.chatapp.mediator.ChatMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ChatClientController implements ChatClient {

    private final String username;
    private final ChatMediator mediator;

    private TextArea chatArea;
    private TextField messageField;
    private ComboBox<String> recipientBox;

    public ChatClientController(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.registerClient(this);
    }

    public void createWindow(List<String> allUsers) {
        Stage stage = new Stage();
        stage.setTitle(username);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setPrefHeight(300);

        messageField = new TextField();
        messageField.setPromptText("Enter message...");

        recipientBox = new ComboBox<>();
        recipientBox.getItems().addAll(allUsers);
        recipientBox.getItems().remove(username);
        recipientBox.setPromptText("Select recipient");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            String message = messageField.getText().trim();
            String recipient = recipientBox.getValue();

            if (!message.isEmpty() && recipient != null) {
                sendMessage(message, recipient);
                messageField.clear();
            }
        });

        VBox layout = new VBox(10, chatArea, recipientBox, messageField, sendButton);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 450);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void sendMessage(String message, String recipient) {
        mediator.sendMessage(message, this, recipient);
    }

    @Override
    public void receiveMessage(String message, String sender) {
        chatArea.appendText(sender + ": " + message + "\n");
    }

    @Override
    public String getUsername() {
        return username;
    }
}