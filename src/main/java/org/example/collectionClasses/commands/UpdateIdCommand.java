package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.InputManager;
import org.example.models.Worker;
import org.example.models.*;
import org.example.models.enums.OrganizationType;
import org.example.models.enums.Status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

public class UpdateIdCommand {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public UpdateIdCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    public String execute(String argument) {
        try {
            if (argument == null || argument.isBlank()) {
                return "Error: ID not provided.";
            }

            int id = Integer.parseInt(argument);
            Worker oldWorker = collectionManager.getById(id);

            if (oldWorker == null) {
                return "Error: No worker with ID " + id + " found.";
            }


            // 1. Собираем новые данные (с сохранением текущих значений по умолчанию)
            System.out.println("Updating worker ID=" + id + ". Enter new values (leave blank to keep current):");

// Имя
            System.out.print("Enter name (current: " + oldWorker.getName() + "):\n> ");
            String name = inputManager.nextLine();
            name = name.isBlank() ? oldWorker.getName() : name;

// Координаты
            System.out.print("Enter coordinates X (current: " + oldWorker.getCoordinates().getX() + "):\n> ");
            String xStr = inputManager.nextLine();
            int x = xStr.isBlank() ? oldWorker.getCoordinates().getX() : Integer.parseInt(xStr);

            System.out.print("Enter coordinates Y (current: " + oldWorker.getCoordinates().getY() + "):\n> ");
            String yStr = inputManager.nextLine();
            double y = yStr.isBlank() ? oldWorker.getCoordinates().getY() : Double.parseDouble(yStr);
            Coordinates newCoordinates = new Coordinates(x, y);

// Зарплата
            System.out.print("Enter salary (current: " + oldWorker.getSalary() + "):\n> ");
            String salaryStr = inputManager.nextLine();
            Long newSalary = salaryStr.isBlank() ? oldWorker.getSalary() : Long.parseLong(salaryStr);

// Дата начала
            System.out.print("Enter start date (yyyy-MM-dd, current: " + oldWorker.getStartDate() + "):\n> ");
            String startDateStr = inputManager.nextLine();
            Date newStartDate = startDateStr.isBlank() ? oldWorker.getStartDate() :
                    Date.from(LocalDate.parse(startDateStr).atStartOfDay(ZoneId.systemDefault()).toInstant());

// Дата окончания
            System.out.print("Enter end date (yyyy-MM-ddTHH:mm:ss±hh:mm, current: " + oldWorker.getEndDate() + "):\n> ");
            String endDateStr = inputManager.nextLine();
            ZonedDateTime newEndDate = endDateStr.isBlank() ? oldWorker.getEndDate() : ZonedDateTime.parse(endDateStr);

// Статус
            System.out.print("Enter status (" + Arrays.toString(Status.values()) +
                    ", current: " + oldWorker.getStatus() + "):\n> ");
            String statusStr = inputManager.nextLine();
            Status newStatus = statusStr.isBlank() ? oldWorker.getStatus() : Status.valueOf(statusStr);

// Организация
            System.out.println("\nOrganization details:");
            System.out.print("Enter turnover (current: " + oldWorker.getOrganization().getAnnualTurnover() + "):\n> ");
            String turnoverStr = inputManager.nextLine();
            float newTurnover = turnoverStr.isBlank() ? oldWorker.getOrganization().getAnnualTurnover() : Float.parseFloat(turnoverStr);

            System.out.print("Enter type (" + Arrays.toString(OrganizationType.values()) +
                    ", current: " + oldWorker.getOrganization().getType() + "):\n> ");
            String typeStr = inputManager.nextLine();
            OrganizationType newType = typeStr.isBlank() ? oldWorker.getOrganization().getType() : OrganizationType.valueOf(typeStr);

// Адрес
            System.out.println("\nAddress details:");
            System.out.print("Enter street (current: " + oldWorker.getOrganization().getOfficialAddress().getStreet() + "):\n> ");
            String street = inputManager.nextLine();
            street = street.isBlank() ? oldWorker.getOrganization().getOfficialAddress().getStreet() : street;

            System.out.print("Enter location X (current: " + oldWorker.getOrganization().getOfficialAddress().getTown().getX() + "):\n> ");
            String lxStr = inputManager.nextLine();
            long lx = lxStr.isBlank() ? oldWorker.getOrganization().getOfficialAddress().getTown().getX() : Long.parseLong(lxStr);

            System.out.print("Enter location Y (current: " + oldWorker.getOrganization().getOfficialAddress().getTown().getY() + "):\n> ");
            String lyStr = inputManager.nextLine();
            double ly = lyStr.isBlank() ? oldWorker.getOrganization().getOfficialAddress().getTown().getY() : Double.parseDouble(lyStr);

            System.out.print("Enter location Z (current: " + oldWorker.getOrganization().getOfficialAddress().getTown().getZ() + "):\n> ");
            String lzStr = inputManager.nextLine();
            Long lz = lzStr.isBlank() ? oldWorker.getOrganization().getOfficialAddress().getTown().getZ() : Long.parseLong(lzStr);

            System.out.print("Enter location name (current: " + oldWorker.getOrganization().getOfficialAddress().getTown().getName() + "):\n> ");
            String locName = inputManager.nextLine();
            locName = locName.isBlank() ? oldWorker.getOrganization().getOfficialAddress().getTown().getName() : locName;

            Location newTown = new Location(lx, ly, lz, locName);
            Address newAddress = new Address(street, newTown);
            Organization newOrganization = new Organization(newTurnover, newType, newAddress);

            // 2. Создаем обновленного работника с сохранением исходного ID
            Worker updatedWorker = Worker.create(
                    name,
                    newCoordinates,
                    newSalary,
                    newStartDate,
                    newEndDate,
                    newStatus,
                    newOrganization
            );
            updatedWorker.setId(id); // Важно сохранить исходный ID

            // 3. Заменяем в коллекции
            collectionManager.removeById(id);
            collectionManager.addWorker(updatedWorker);

            return "Worker ID=" + id + " updated successfully.";

        } catch (NumberFormatException e) {
            return "Error: Invalid ID format.";
        } catch (Exception e) {
            return "Error during update: " + e.getMessage();
        }
    }
}