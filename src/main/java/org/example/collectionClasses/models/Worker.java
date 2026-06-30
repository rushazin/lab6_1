package org.example.models;

import lombok.Setter;
import org.example.models.enums.OrganizationType;
import org.example.models.enums.Status;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Main class representing a Worker.
 */
public class Worker {
    private static int idCounter = 1;

    @Setter
    private int id; // auto
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate; // auto
    private Long salary;
    private Date startDate;
    private ZonedDateTime endDate;
    private Status status;
    private Organization organization;

    public Worker(int id, String name, Coordinates coordinates, LocalDateTime creationDate,
                  Long salary, Date startDate, ZonedDateTime endDate,
                  Status status, Organization organization) {
        if (id <= 0) throw new IllegalArgumentException("id must be > 0");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or empty");
        if (coordinates == null) throw new IllegalArgumentException("coordinates cannot be null");
        if (creationDate == null) throw new IllegalArgumentException("creationDate cannot be null");
        if (startDate == null) throw new IllegalArgumentException("startDate cannot be null");
        if (status == null) throw new IllegalArgumentException("status cannot be null");

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.organization = organization;
    }

    public static Worker create(String name, Coordinates coordinates, Long salary,
                                Date startDate, ZonedDateTime endDate,
                                Status status, Organization organization) {
        return new Worker(idCounter++, name, coordinates, LocalDateTime.now(),
                salary, startDate, endDate, status, organization);
    }

    public static Worker fromCSV(String csvLine) {
        try {
            String[] tokens = csvLine.split(",", -1);
            if (tokens.length != 16) {
                throw new IllegalArgumentException("Invalid CSV format for Worker");
            }

            int index = 0;
            int id = Integer.parseInt(tokens[index++]);
            String name = tokens[index++];
            int coordX = Integer.parseInt(tokens[index++]);
            double coordY = Double.parseDouble(tokens[index++]);
            LocalDateTime creationDate = LocalDateTime.parse(tokens[index++]);
            Long salary = tokens[index].isEmpty() ? null : Long.parseLong(tokens[index]);
            index++;
            Date startDate = new Date(Long.parseLong(tokens[index++]));
            ZonedDateTime endDate = tokens[index].isEmpty() ? null : ZonedDateTime.parse(tokens[index]);
            index++;
            Status status = Status.valueOf(tokens[index++]);
            Float turnover = Float.parseFloat(tokens[index++]);
            OrganizationType orgType = tokens[index].isEmpty() ? null : OrganizationType.valueOf(tokens[index]);
            index++;
            long locX = Long.parseLong(tokens[index++]);
            double locY = Double.parseDouble(tokens[index++]);
            Long locZ = Long.parseLong(tokens[index++]);
            String locName = tokens[index++];
            String street = tokens[index++];

            Coordinates coordinates = new Coordinates(coordX, coordY);
            Location location = new Location(locX, locY, locZ, locName);
            Address address = new Address(street, location);
            Organization organization = new Organization(turnover, orgType, address);

            return new Worker(id, name, coordinates, creationDate, salary, startDate, endDate, status, organization);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid CSV format for Worker", e);
        }
    }

    public String toCSV() {
        return id + "," +
                name + "," +
                coordinates.getX() + "," +
                coordinates.getY() + "," +
                creationDate + "," +
                (salary != null ? salary : "") + "," +
                startDate.getTime() + "," +
                (endDate != null ? endDate : "") + "," +
                status + "," +
                organization.getAnnualTurnover() + "," +
                (organization.getType() != null ? organization.getType() : "") + "," +
                organization.getOfficialAddress().getTown().getX() + "," +
                organization.getOfficialAddress().getTown().getY() + "," +
                organization.getOfficialAddress().getTown().getZ() + "," +
                (organization.getOfficialAddress().getTown().getName() != null ? organization.getOfficialAddress().getTown().getName() : "") + "," +
                (organization.getOfficialAddress().getStreet() != null ? organization.getOfficialAddress().getStreet() : "");
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", organization=" + organization +
                '}';
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Status getStatus() {
        return status;
    }

    public Organization getOrganization() {
        return organization;
    }

}
