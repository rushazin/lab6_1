package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.InputManager;
import org.example.models.*;
import org.example.models.enums.*;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Adds a new Worker to the collection.
 */
public class AddCommand {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public AddCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    public String execute(String argument) {
        try {
            System.out.print("Enter name:\n> ");
            String name = inputManager.nextLine();

            System.out.print("Enter coordinates X (int <= 612):\n> ");
            int x = Integer.parseInt(inputManager.nextLine());

            System.out.print("Enter coordinates Y (double):\n> ");
            double y = Double.parseDouble(inputManager.nextLine());
            Coordinates coordinates = new Coordinates(x, y);

            System.out.print("Enter salary (leave blank for null):\n> ");
            String salaryStr = inputManager.nextLine();
            Long salary = salaryStr.isBlank() ? null : Long.parseLong(salaryStr);

            System.out.print("Enter start date (yyyy-MM-dd):\n> ");
            Date startDate = Date.from(java.time.LocalDate
                    .parse(inputManager.nextLine())
                    .atStartOfDay(java.time.ZoneId.systemDefault())
                    .toInstant());

            System.out.print("Enter end date (yyyy-MM-ddTHH:mm:ss±hh:mm or leave blank for null):\n> ");
            String endStr = inputManager.nextLine();
            ZonedDateTime endDate = endStr.isBlank() ? null : ZonedDateTime.parse(endStr);

            System.out.print("Enter status (FIRED, HIRED, REGULAR, PROBATION):\n> ");
            Status status = Status.valueOf(inputManager.nextLine());

            System.out.print("Enter organization turnover (float):\n> ");
            float turnover = Float.parseFloat(inputManager.nextLine());

            System.out.print("Enter organization type (GOVERNMENT, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY or leave blank for null):\n> ");
            String typeStr = inputManager.nextLine();
            OrganizationType type = typeStr.isBlank() ? null : OrganizationType.valueOf(typeStr);

            System.out.print("Enter address street (can be empty):\n> ");
            String street = inputManager.nextLine();
            street = street.isBlank() ? null : street;

            System.out.print("Enter location X (long):\n> ");
            long lx = Long.parseLong(inputManager.nextLine());

            System.out.print("Enter location Y (double):\n> ");
            double ly = Double.parseDouble(inputManager.nextLine());

            System.out.print("Enter location Z (long):\n> ");
            Long lz = Long.parseLong(inputManager.nextLine());

            System.out.print("Enter location name (can be empty):\n> ");
            String lname = inputManager.nextLine();
            lname = lname.isBlank() ? null : lname;

            Location town = new Location(lx, ly, lz, lname);
            Address address = new Address(street, town);
            Organization organization = new Organization(turnover, type, address);

            Worker worker = Worker.create(name, coordinates, salary, startDate, endDate, status, organization);
            collectionManager.addWorker(worker);
            return "Worker added.";
        } catch (Exception e) {
            return "Error adding worker: " + e.getMessage();
        }
    }
}
