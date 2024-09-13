package com.chatroom.testchatjfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;


public class ServerController implements Initializable {
    private Server server;
    // GUI components and event handlers
    @FXML
    private Text text;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void setText() {
        server.setMessage((message) -> {
            Platform.runLater(() -> {
                text.setText(message + "!");
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize GUI components and event handlers
        new Thread(new Server(3000)).start();

        Client client = null;
        try {
            client = new Client("localhost", 3000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(client).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        server.setMessage((message) -> {
            Platform.runLater(() -> {
                text.setText(message + "!");
            });
        });

        while (true) {
            String message = null;
            try {
                message = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            client.send(message);
        }

        // Example: Update the GUI form the background thread
//        Platform.runLater(() -> {
//            // Update GUI elements
//        });
    }



//    public static void updateText(String message) {
//        Platform.runLater(() ->
//            text.setText(message + "!")
//        );
//    }

}