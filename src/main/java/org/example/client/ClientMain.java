package org.example.client;

import org.example.collectionClasses.commands.*;
import org.example.collectionClasses.models.*;
import org.example.collectionClasses.models.enums.OrganizationType;
import org.example.collectionClasses.models.enums.Status;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

public class ClientMain {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;
    private SocketChannel socketChannel;

    public static void main(String[] args) {
        ClientMain client = new ClientMain();
        client.start();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            connectToServer();
            System.out.println("Connected to server. Type commands or 'exit' to quit");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    String response = sendCommandAndGetResponse(input);
                    System.out.println("Server response:\n" + response);
                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                    reconnect();
                }
            }
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void connectToServer() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
    }

    private void reconnect() {
        disconnect();
        try {
            connectToServer();
            System.out.println("Reconnected to server");
        } catch (IOException e) {
            System.err.println("Failed to reconnect: " + e.getMessage());
        }
    }

    private void disconnect() {
        try {
            if (socketChannel != null && socketChannel.isConnected()) {
                socketChannel.close();
            }
        } catch (IOException e) {
            System.err.println("Error while disconnecting: " + e.getMessage());
        }
    }

    private String sendCommandAndGetResponse(String command) throws IOException {
        // Отправка команды
        ByteBuffer buffer = ByteBuffer.wrap((command + "\n").getBytes());
        socketChannel.write(buffer);

        // Получение ответа
        ByteBuffer responseBuffer = ByteBuffer.allocate(8192);
        int bytesRead = socketChannel.read(responseBuffer);

        if (bytesRead == -1) {
            throw new IOException("Connection closed by server");
        }

        return new String(responseBuffer.array(), 0, bytesRead).trim();
    }
}