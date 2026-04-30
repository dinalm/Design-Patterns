package com.chatapp;

import com.chatapp.client.ChatClientController;
import com.chatapp.mediator.ChatMediator;
import com.chatapp.mediator.ChatRoomMediator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ChatMediator mediator = new ChatRoomMediator();

        List<String> usernames = Arrays.asList("Alice", "Bob", "Charlie");

        ChatClientController alice = new ChatClientController("Alice", mediator);
        ChatClientController bob = new ChatClientController("Bob", mediator);
        ChatClientController charlie = new ChatClientController("Charlie", mediator);

        alice.createWindow(usernames);
        bob.createWindow(usernames);
        charlie.createWindow(usernames);
    }

    public static void main(String[] args) {
        launch(args);
    }
}