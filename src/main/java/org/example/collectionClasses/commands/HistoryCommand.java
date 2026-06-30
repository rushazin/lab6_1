package org.example.commands;

import org.example.managers.CommandManager;

import java.util.Collections;
import java.util.List;

public class HistoryCommand {
    private final CommandManager commandManager;

    public HistoryCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String execute() {
        List<String> historyList = Collections.singletonList(commandManager.getHistory());

        StringBuilder sb = new StringBuilder("Command history:\n");
        for (String cmd : historyList) {
            sb.append(cmd).append("\n");
        }
        return sb.toString();
    }
}