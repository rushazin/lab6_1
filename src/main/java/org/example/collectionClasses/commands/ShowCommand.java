package org.example.commands;

import org.example.managers.CollectionManager;

/**
 * Outputs all elements in the collection.
 */
public class ShowCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String argument) {
        if (!argument.isBlank()) {
            return "This command does not take any arguments.";
        }
        return collectionManager.show();
    }
}