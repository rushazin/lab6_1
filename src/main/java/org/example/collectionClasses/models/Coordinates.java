package org.example.models;

import lombok.Getter;

@Getter
public class Coordinates {
    private Integer x; // Max: 612, Cannot be null
    private double y;

    public Coordinates(Integer x, double y) {
        if (x == null) {
            throw new IllegalArgumentException("Coordinate x cannot be null.");
        }
        if (x > 612) {
            throw new IllegalArgumentException("Coordinate x cannot be greater than 612.");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";
    }
}
