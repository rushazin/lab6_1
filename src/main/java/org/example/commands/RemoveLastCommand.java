package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Worker;

import java.util.Stack;

/**
 * Removes the last (top) element from the collection.
 */
public class RemoveLastCommand {
    private final CollectionManager collectionManager;

    public RemoveLastCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        try {
            Stack<Worker> stack = collectionManager.getCollection();
            if (stack.isEmpty()) {
                return "The collection is already empty.";
            }
            Worker removed = stack.pop();
            return "Removed last worker with ID: " + removed.getId();
        } catch (Exception e) {
            return "Failed to remove last worker: " + e.getMessage();
        }
    }
}
