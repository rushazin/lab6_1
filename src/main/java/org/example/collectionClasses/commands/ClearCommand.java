package org.example.commands;

import org.example.managers.CollectionManager;


public class ClearCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String argument) {
        if (!argument.isBlank()) {
            return "This command does not take arguments.";
        }
        collectionManager.clear();
        return "Collection cleared.";
    }
}