package org.example.commands;

import org.example.managers.CollectionManager;

public class InfoCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String argument) {
        if (!argument.isBlank()) {
            return "This command does not take any arguments.";
        }
        return collectionManager.getInfo();
    }
}