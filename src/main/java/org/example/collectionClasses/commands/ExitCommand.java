package org.example.commands;

public class ExitCommand {
    public String execute(String argument) {
        if (!argument.isBlank()) {
            return "This command does not take arguments.";
        }
        System.out.println("Exiting without saving.");
        System.exit(0);
        return null;
    }
}