package com.codeWithDivya.dao;

import com.codeWithDivya.utils.Direction;

import java.util.Date;
import java.util.Objects;

public class Fireball {

    //date/time of peak brightness (GMT)
    private Date date;
    //approximate total radiated energy (1010 joules)
    private double energy;
    //approximate total impact energy (kt)
    private double totalImpactEnergy;
    //latitude at peak brightness (degrees)
    private double latitude;
    //latitude direction (“N” or “S”)
    private Direction latitutdeDirection;
    //longitude at peak brightness (degrees)
    private double longitude;
    //longitude direction (“E” or “W”)
    private Direction longitudeDirection;
    //altitude above the geoid at peak brightness (km)
    private double altitude;
    //velocity at peak brightness (km/s)
    private double velocity;
    //pre-entry estimated velocity (Earth centered X component, km/s)
    private double vx;
    //pre-entry est. velocity (Earth centered Y component, km/s)
    private double vy;
    //pre-entry est. velocity (Earth centered Z component, km/s)
    private double vz;

    public Fireball() {
    }

    public Fireball(double totalImpactEnergy, double latitude, Direction latitutdeDirection, double longitude, Direction longitudeDirection) {
        this.totalImpactEnergy = totalImpactEnergy;
        this.latitude = latitude;
        this.latitutdeDirection = latitutdeDirection;
        this.longitude = longitude;
        this.longitudeDirection = longitudeDirection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getTotalImpactEnergy() {
        return totalImpactEnergy;
    }

    public void setTotalImpactEnergy(double totalImpactEnergy) {
        this.totalImpactEnergy = totalImpactEnergy;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Direction getLatitutdeDirection() {
        return latitutdeDirection;
    }

    public void setLatitutdeDirection(Direction latitutdeDirection) {
        this.latitutdeDirection = latitutdeDirection;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Direction getLongitudeDirection() {
        return longitudeDirection;
    }

    public void setLongitudeDirection(Direction longitudeDirection) {
        this.longitudeDirection = longitudeDirection;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fireball)) return false;
        Fireball fireball = (Fireball) o;
        return Double.compare(fireball.getEnergy(), getEnergy()) == 0 &&
                Double.compare(fireball.getTotalImpactEnergy(), getTotalImpactEnergy()) == 0 &&
                Double.compare(fireball.getLatitude(), getLatitude()) == 0 &&
                Double.compare(fireball.getLongitude(), getLongitude()) == 0 &&
                Double.compare(fireball.getAltitude(), getAltitude()) == 0 &&
                Double.compare(fireball.getVelocity(), getVelocity()) == 0 &&
                Double.compare(fireball.getVx(), getVx()) == 0 &&
                Double.compare(fireball.getVy(), getVy()) == 0 &&
                Double.compare(fireball.getVz(), getVz()) == 0 &&
                Objects.equals(getDate(), fireball.getDate()) &&
                getLatitutdeDirection() == fireball.getLatitutdeDirection() &&
                getLongitudeDirection() == fireball.getLongitudeDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getEnergy(), getTotalImpactEnergy(), getLatitude(), getLatitutdeDirection(), getLongitude(), getLongitudeDirection(), getAltitude(), getVelocity(), getVx(), getVy(), getVz());
    }
}