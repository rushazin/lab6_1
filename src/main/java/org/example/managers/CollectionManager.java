package org.example.managers;

import org.example.models.Worker;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Manages the collection of Worker objects.
 */
public class CollectionManager {
    private final Stack<Worker> workerStack;
    private final ZonedDateTime initializationDate;

    public CollectionManager(Stack<Worker> loadedStack) {
        this.workerStack = loadedStack;
        this.initializationDate = ZonedDateTime.now();
    }

    public void addWorker(Worker worker) {
        workerStack.push(worker);
    }

    public Stack<Worker> getCollection() {
        return workerStack;
    }

    public String getInfo() {
        return "Collection type: " + workerStack.getClass().getName() + "\n" +
                "Initialization date: " + initializationDate + "\n" +
                "Number of elements: " + workerStack.size();
    }

    public void clear() {
        workerStack.clear();
    }

    public String show() {
        if (workerStack.isEmpty()) {
            return "The collection is empty.";
        }
        StringBuilder sb = new StringBuilder();
        for (Worker w : workerStack) {
            sb.append(w).append("\n");
        }
        return sb.toString();
    }

    public boolean removeById(long id) {
        return workerStack.removeIf(worker -> worker.getId() == id);
    }

    public void reorder() {
        Collections.reverse(workerStack);
    }

    public long sumOfSalary() {
        return workerStack.stream()
                .filter(w -> w.getSalary() != null)
                .mapToLong(Worker::getSalary)
                .sum();
    }

    public List<Worker> filterLessThanEndDate(ZonedDateTime date) {
        return workerStack.stream()
                .filter(w -> w.getEndDate() != null && w.getEndDate().isBefore(date))
                .toList();
    }

    public Worker getById(int id) {
        for (Worker worker : workerStack) {
            if (worker.getId() == id) {
                return worker;
            }
        }
        return null;
    }
}
