package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.Worker;

import java.time.ZonedDateTime;
import java.util.List;

public class FilterLessThanEndDateCommand {
    private final CollectionManager collectionManager;

    public FilterLessThanEndDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String argument) {
        try {
            ZonedDateTime date = ZonedDateTime.parse(argument.trim());
            List<Worker> filtered = collectionManager.filterLessThanEndDate(date);
            if (filtered.isEmpty()) {
                return "No workers found with endDate less than " + date + ".";
            }
            StringBuilder sb = new StringBuilder();
            for (Worker worker : filtered) {
                sb.append(worker).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Invalid date format.";
        }
    }
}
