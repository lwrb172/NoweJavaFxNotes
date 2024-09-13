package com.chatroom.testchatjfx;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class Client implements Runnable {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private Consumer<String> display;

    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);
    }

    public void setDisplay(Consumer<String> display) {
        this.display = display;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                display.accept(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        writer.println(message);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 3000);
        new Thread(client).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String message = reader.readLine();
            client.send(message);
        }
    }
}


