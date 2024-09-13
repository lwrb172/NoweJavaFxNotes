package com.chatroom.testchatjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatRoomApplication extends Application {
    private ServerController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatRoomApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        controller = (ServerController) fxmlLoader.getController();
        stage.setScene(scene);
        stage.show();


//        new Thread(new Server(3000)).start();
    }

    public static void main(String[] args) {
        launch();
    }

//    import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;  
//
//            import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;  
//
//            import javafx.stage.Stage;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.function.Consumer;
//
//    public class ChatClient  
//            extends Application {
//
//        private Socket socket;
//        private PrintWriter out;
//        private BufferedReader in;
//        private TextArea messagesArea;
//
//        @Override
//        public void start(Stage primaryStage) {
//            // Create the GUI components
//            messagesArea = new TextArea();
//            TextField inputField = new TextField();
//            Button sendButton = new Button("Send");
//
//            // Set up the event handler for the send button
//            sendButton.setOnAction(event -> {
//                String message = inputField.getText();
//                if (!message.isEmpty()) {
//                    out.println(message);
//                    inputField.clear();
//                }
//            });
//
//            // Create the layout
//            VBox root = new VBox(10);
//            root.getChildren().addAll(messagesArea, inputField, sendButton);
//
//            // Create the scene
//            Scene scene = new Scene(root, 400, 300);
//
//            // Set the stage
//            primaryStage.setTitle("Chat Client");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//            // Start a new thread to listen for incoming messages
//            new Thread(() -> {
//                try {
//                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    Consumer<String> updateMessages = message -> {
//                        Platform.runLater(() -> {
//                            messagesArea.appendText(message + "\n");
//                        });
//                    };
//                    String message;
//                    while ((message = in.readLine()) != null) {
//                        updateMessages.accept(message);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//
//        public static void main(String[] args) throws IOException {
//            // Connect to the server
//            socket = new Socket("localhost", 12345);
//            out = new PrintWriter(socket.getOutputStream(), true);
//
//            // Launch the JavaFX application
//            launch(args);
//        }
//    }
}