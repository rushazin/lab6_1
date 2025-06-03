package org.example.models;

import lombok.Getter;

@Getter
public class Address {
    private final String street;
    private final Location town;

    public Address(String street, Location town) {
        if (town == null) throw new IllegalArgumentException("Town must not be null");
        if (street != null && street.isBlank()) throw new IllegalArgumentException("Street must not be empty if set");
        this.street = street;
        this.town = town;
    }

    @Override
    public String toString() {
        return "Address{street='" + street + "', town=" + town + '}';
    }
}
