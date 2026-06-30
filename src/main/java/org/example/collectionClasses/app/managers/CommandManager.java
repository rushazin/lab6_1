package org.example.managers;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

/**
 * Registers and executes available commands by name.
 */
public class CommandManager {
    private final Map<String, Consumer<String[]>> commands = new HashMap<>();
    private static final int HISTORY_LIMIT = 8;
    private final LinkedList<String> commandHistory = new LinkedList<>();
    private boolean isScriptRunning = false;
    private String currentScriptPath = null;
    private final Deque<String> scriptCallStack = new ArrayDeque<>();  // Стек вызовов скриптов
    private final Set<String> executedScripts = new HashSet<>();        // Множество выполняемых скриптов

    /**
     * Registers a command by name.
     * @param name Command name
     * @param action Logic to run for the command
     */
    public void register(String name, Consumer<String[]> action) {
        commands.put(name.toLowerCase(), action);
    }

    /**
     * Executes a command by input line.
     * @param inputLine Full user input
     * @return true if command was found and executed
     */

    public boolean execute(String inputLine) {
        if (inputLine == null || inputLine.trim().isEmpty()) {
            return false;
        }

        String[] parts = inputLine.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        // Обработка execute_script
        if (commandName.equals("execute_script")) {
            try {
                String scriptPath = new File(argument).getCanonicalPath();

                if (executedScripts.contains(scriptPath)) {
                    System.err.println("Ошибка: Скрипт '" + scriptPath + "' уже выполнялся!");
                    return false;
                }

                executedScripts.add(scriptPath);  // Помечаем как выполненный

            } catch (IOException e) {
                System.err.println("Ошибка обработки пути: " + e.getMessage());
                return false;
            }
        }

        try {
            Consumer<String[]> command = commands.get(commandName);
            if (command != null) {
                command.accept(new String[]{argument});
                return true;
            }
            System.out.println("Неизвестная команда: " + commandName);
            return false;
        } finally {
            if (commandName.equals("execute_script")) {
                isScriptRunning = false;
                currentScriptPath = null;  // Сброс при завершении
            }
        }
    }

    /**
     * Adds a command to the history.
     * @param commandName the name of the command
     */
    public void addToHistory(String commandName) {
        if (commandName == null || commandName.isBlank() || commandName.equals("history")) return;
        if (commandHistory.size() >= HISTORY_LIMIT) {
            commandHistory.removeFirst();
        }
        commandHistory.add(commandName);
    }

    /**
     * Returns the list of recently used commands.
     * @return formatted history string
     */
    public String getHistory() {
        if (commandHistory.isEmpty()) {
            return "History is empty.";
        }

        StringBuilder sb = new StringBuilder("Recent commands:\n");
        for (String cmd : commandHistory) {
            sb.append("- ").append(cmd).append("\n");
        }
        return sb.toString();
    }
    public void clearScriptsCache() {
        executedScripts.clear();  // Для очистки при новой сессии
    }
}
