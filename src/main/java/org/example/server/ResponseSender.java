package org.example.server;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import java.io.Serializable;
import java.util.Collection;

import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;

public class ResponseSender {
    private final SocketChannel clientChannel;

    public ResponseSender(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    public void sendResponse(Object response) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(clientChannel.socket().getOutputStream())) {
            oos.writeObject(response);
        }
    }
}