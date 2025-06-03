package org.example.commands;

import org.example.managers.CollectionManager;

public class RemoveByIdCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String argument) {
        try {
            if (argument == null || argument.trim().isEmpty()) {
                return "Missing argument: id";
            }

            long id = Long.parseLong(argument.trim());
            boolean removed = collectionManager.removeById(id);
            return removed
                    ? "Worker with id " + id + " removed."
                    : "Worker with id " + id + " not found.";
        } catch (NumberFormatException e) {
            return "Invalid ID format.";
        }
    }
}