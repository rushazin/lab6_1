package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Worker;

import java.util.Comparator;
import java.util.List;

public class PrintFieldDescendingStatusCommand  {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingStatusCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        List<Worker> sorted = collectionManager.getCollection().stream()
                .sorted(Comparator.comparing(Worker::getStatus).reversed())
                .toList();
        StringBuilder sb = new StringBuilder();
        for (Worker worker : sorted) {
            sb.append(worker.getStatus()).append("\n");
        }
        return sb.toString();
    }
}
