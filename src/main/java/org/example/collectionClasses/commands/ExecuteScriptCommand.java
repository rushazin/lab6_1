package org.example.commands;

import org.example.managers.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExecuteScriptCommand  {
    private final CommandManager commandManager;

    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String execute(String argument) {
        File scriptFile = new File(argument.trim());
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            return "Script file not found.";
        }

        try (Scanner scanner = new Scanner(scriptFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    commandManager.execute(line);
                }
            }
            return "Script executed successfully.";
        } catch (FileNotFoundException e) {
            return "Error reading script file.";
        }
    }
}