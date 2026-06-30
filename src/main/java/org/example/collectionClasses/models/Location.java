package org.example.models;

public class Location {
    private long x;
    private double y;
    private Long z; // Cannot be null
    private String name; // Can be null

    public Location(long x, double y, Long z, String name) {
        if (z == null) throw new IllegalArgumentException("z cannot be null");
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + ", z=" + z + ", name='" + name + "'}";
    }
}
