package com.chatroom.testchatjfx;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Server implements Runnable {
    private final int port;
    private List<ClientHandler> handlers = new ArrayList<>();
    private Consumer<String> messageConsumer;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);
            while (true) {
                Socket clienSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clienSocket, this);
                handlers.add(handler);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        handlers.forEach(clientHandler -> clientHandler.send(message));
//        // Update GUI with the new message
//        Platform.runLater(() -> {
//            ServerController.updateText(message);
//        });
        if (messageConsumer != null) {
            messageConsumer.accept(message);
        }
    }

    public void setMessage(Consumer<String> message) {
        this.messageConsumer = message;
    }

    public String getMessage(String message) {
        String msg = message;
        return message;
    }

    public void removeHandler(ClientHandler handler) {
        handlers.remove(handler);
    }

    public void disconnectHandlers() {
        handlers.forEach(handler -> handler.send("Bye!"));
        handlers.clear();
    }

//    public static void main(String[] args) throws IOException {
//        Server server = new Server();
//
//        Runtime.getRuntime().addShutdownHook(new Thread(server::disconnectHandlers));
//
//        server.listen();
//    }
}