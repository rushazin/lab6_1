package org.example.commands;

import org.example.managers.CollectionManager;

public class ReorderCommand {
    private final CollectionManager collectionManager;

    public ReorderCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        collectionManager.reorder();
        return "Collection order reversed.";
    }
}
