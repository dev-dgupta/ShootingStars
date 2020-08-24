package com.codeWithDivya.dao;

import com.codeWithDivya.utils.Direction;

import java.util.Objects;

public class Location {

    private String name;
    private double latitude;
    private double longitude;
    private double shootingStarBrightness;
    private Direction latDirection;
    private Direction longDirection;

    public Location(String name, double latitude, double longitude, Direction latDirection, Direction longDirection) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latDirection = latDirection;
        this.longDirection = longDirection;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Direction getLatDirection() {
        return latDirection;
    }

    public void setLatDirection(Direction latDirection) {
        this.latDirection = latDirection;
    }

    public Direction getLongDirection() {
        return longDirection;
    }

    public void setLongDirection(Direction longDirection) {
        this.longDirection = longDirection;
    }

    public double getShootingStarBrightness() {
        return shootingStarBrightness;
    }

    public void setShootingStarBrightness(double shootingStarBrightness) {
        this.shootingStarBrightness = shootingStarBrightness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.getLatitude(), getLatitude()) == 0 &&
                Double.compare(location.getLongitude(), getLongitude()) == 0 &&
                Double.compare(location.getShootingStarBrightness(), getShootingStarBrightness()) == 0 &&
                Objects.equals(getName(), location.getName()) &&
                getLatDirection() == location.getLatDirection() &&
                getLongDirection() == location.getLongDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLatitude(), getLongitude(), getShootingStarBrightness(), getLatDirection(), getLongDirection());
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", shootingStarBrightness=" + shootingStarBrightness +
                ", latDirection=" + latDirection +
                ", longDirection=" + longDirection +
                '}';
    }
}
