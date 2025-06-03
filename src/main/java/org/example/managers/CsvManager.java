package org.example.managers;

import org.example.models.Worker;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 * Handles reading and writing Worker data from/to a CSV file.
 */
public class  CsvManager {
    private final String fileName;

    public CsvManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads Workers from a CSV file into a Stack.
     */
    public Stack<Worker> load() {
        Stack<Worker> stack = new Stack<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Worker worker = Worker.fromCSV(line);
                        stack.push(worker);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Skipping invalid line: " + line);
                        System.err.println("Reason: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + fileName);
        } catch (Exception e) {
            System.err.println("Error loading CSV: " + e.getMessage());
        }
        return stack;
    }

    /**
     * Saves the Stack of Workers to a CSV file.
     */
    public void save(Stack<Worker> stack) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Worker worker : stack) {
                writer.write(worker.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }
}
