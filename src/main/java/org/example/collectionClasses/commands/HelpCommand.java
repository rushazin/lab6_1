package org.example.commands;

/**
 * Displays help information for all commands.
 */
public class HelpCommand {
    public String execute(String argument) {
        if (!argument.isBlank()) {
            return "This command does not take any arguments.";
        }
        return """
                Available commands:
                - help : вывести справку по доступным командам
                - info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                - show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                - add : добавить новый элемент в коллекцию
                - update_id {element} : обновить значение элемента коллекции, id которого равен заданному
                - remove_by_id id : удалить элемент из коллекции по его id
                - clear : очистить коллекцию
                - save : сохранить коллекцию в файл
                - execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                - exit : завершить программу (без сохранения в файл)
                - remove_last : удалить последний элемент из коллекции
                - reorder : отсортировать коллекцию в порядке, обратном нынешнему
                - history : вывести последние 8 команд (без их аргументов)
                - sum_of_salary : вывести сумму значений поля salary для всех элементов коллекции
                - filter_less_than_end_date endDate : вывести элементы, значение поля endDate которых меньше заданного
                - print_field_descending_status : вывести значения поля status всех элементов в порядке убывания
                """;
    }
}