package org.example;

import org.example.commands.*;
import org.example.managers.*;
import org.example.models.Worker;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        try {

            Scanner inputScanner = new Scanner(System.in);
            CsvManager csvManager = new CsvManager("workers.csv");
            CollectionManager collectionManager = new CollectionManager(csvManager.load());
            InputManager inputManager = new InputManager(inputScanner);
            CommandManager commandManager = new CommandManager();

            // Создаем команды
            AddCommand addCommand = new AddCommand(collectionManager, inputManager);
            ClearCommand clearCommand = new ClearCommand(collectionManager);
            ExitCommand exitCommand = new ExitCommand();
            HelpCommand helpCommand = new HelpCommand();
            InfoCommand infoCommand = new InfoCommand(collectionManager);
            SaveCommand saveCommand = new SaveCommand(collectionManager, csvManager);
            ShowCommand showCommand = new ShowCommand(collectionManager);
            ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(commandManager);
            FilterLessThanEndDateCommand filter = new FilterLessThanEndDateCommand(collectionManager);
            HistoryCommand historyCommand = new HistoryCommand(commandManager);
            PrintFieldDescendingStatusCommand print = new PrintFieldDescendingStatusCommand(collectionManager);
            RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand(collectionManager);
            ReorderCommand reorderCommand = new ReorderCommand(collectionManager);
            SumOfSalaryCommand sumOfSalaryCommand = new SumOfSalaryCommand(collectionManager);
            UpdateIdCommand updateIdCommand = new UpdateIdCommand(collectionManager, inputManager);
            RemoveLastCommand removeLastCommand = new RemoveLastCommand(collectionManager);

            // Создаем и регистрируем команды в менеджере

            commandManager.register("exit", args1 -> {
                exitCommand.execute("");
                System.exit(0);
            });


            commandManager.register("add", args1 -> {
                commandManager.addToHistory("add");
                System.out.println(addCommand.execute(""));
            });
            commandManager.register("info", args1 -> {
                commandManager.addToHistory("info");
                System.out.println(infoCommand.execute(""));
            });
            commandManager.register("clear", args1 -> {
                commandManager.addToHistory("clear");
                System.out.println(clearCommand.execute(""));
            });
            commandManager.register("save", args1 -> {
                commandManager.addToHistory("save");
                System.out.println(saveCommand.execute());
            });
            commandManager.register("help", args1 -> {
                commandManager.addToHistory("help");
                System.out.println(helpCommand.execute(""));
            });
            commandManager.register("show", args1 -> {
                commandManager.addToHistory("show");
                System.out.println(showCommand.execute(""));
            });

            commandManager.register("history", args1 -> System.out.println(historyCommand.execute()));


            commandManager.register("print_field_descending_status", args1 -> {
                commandManager.addToHistory("print_field_descending_status");
                System.out.println(print.execute());
            });
            commandManager.register("reorder", args1 -> {
                commandManager.addToHistory("reorder");
                System.out.println(reorderCommand.execute());
            });
            commandManager.register("sum_of_salary", args1 -> {
                commandManager.addToHistory("sum_of_salary");
                System.out.println(sumOfSalaryCommand.execute());
            });
            commandManager.register("remove_last", args1 -> {
                commandManager.addToHistory("remove_last");
                System.out.println(removeLastCommand.execute());
            });


            commandManager.register("remove_by_id", arg -> {
                commandManager.addToHistory("remove_by_id");
                System.out.println(removeByIdCommand.execute(arg.length > 0 ? arg[0] : ""));
            });
            commandManager.register("filter_less_than_end_date", args1 -> {
                commandManager.addToHistory("filter_less_than_end_date");
                System.out.println(filter.execute(args1.length > 0 ? args1[0] : ""));
            });
            commandManager.register("update_id", args1 -> {
                commandManager.addToHistory("update_id");
                System.out.println(updateIdCommand.execute(args1.length > 0 ? args1[0] : ""));
            });
            commandManager.register("execute_script", args1 -> {
                commandManager.addToHistory("execute_script");
                System.out.println(executeScriptCommand.execute(args1.length > 0 ? args1[0] : ""));
            });



            // Основной цикл
            while (true) {
                System.out.print("> ");
                String inputLine = inputScanner.nextLine().trim();
                if (!commandManager.execute(inputLine)) {
                    System.out.println("Unknown command. Type 'help' to see available commands.");
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred during program execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
