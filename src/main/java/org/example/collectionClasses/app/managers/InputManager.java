package org.example.managers;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private final Scanner scanner;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads the next line from input
     * @return trimmed user input, or null if stream is closed
     */
    public String nextLine() {
        try {
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Input stream closed unexpectedly.");
        }
        return null;
    }
}
