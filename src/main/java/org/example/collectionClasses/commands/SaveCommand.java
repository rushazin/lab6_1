package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.CsvManager;

/**
 * Saves the collection to CSV.
 */
public class SaveCommand {
    private final CollectionManager collectionManager;
    private final CsvManager csvManager;

    public SaveCommand(CollectionManager collectionManager, CsvManager csvManager) {
        this.collectionManager = collectionManager;
        this.csvManager = csvManager;
    }

    public String execute() {
        csvManager.save(collectionManager.getCollection());
        return "Collection saved.";
    }
}