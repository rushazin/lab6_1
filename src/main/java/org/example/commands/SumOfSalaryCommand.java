package org.example.commands;

import org.example.managers.CollectionManager;

public class SumOfSalaryCommand {
    private final CollectionManager collectionManager;

    public SumOfSalaryCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        long sum = collectionManager.sumOfSalary();
        return "Sum of salaries: " + sum;
    }
}
