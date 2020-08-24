package com.codeWithDivya.dao;

import com.codeWithDivya.utils.Direction;

import java.util.Date;
import java.util.Objects;

public class Fireball {

    //date/time of peak brightness (GMT)
    private Date date;
    //approximate total radiated energy (1010 joules)
    private float energy;
    //approximate total impact energy (kt)
    private float totalImpactEnergy;
    //latitude at peak brightness (degrees)
    private float latitude;
    //latitude direction (“N” or “S”)
    private Direction latitutdeDirection;
    //longitude at peak brightness (degrees)
    private float longitude;
    //longitude direction (“E” or “W”)
    private Direction longitudeDirection;
    //altitude above the geoid at peak brightness (km)
    private float altitude;
    //velocity at peak brightness (km/s)
    private float velocity;
    //pre-entry estimated velocity (Earth centered X component, km/s)
    private float vx;
    //pre-entry est. velocity (Earth centered Y component, km/s)
    private float vy;
    //pre-entry est. velocity (Earth centered Z component, km/s)
    private float vz;

    public Fireball() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getTotalImpactEnergy() {
        return totalImpactEnergy;
    }

    public void setTotalImpactEnergy(float totalImpactEnergy) {
        this.totalImpactEnergy = totalImpactEnergy;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Direction getLatitutdeDirection() {
        return latitutdeDirection;
    }

    public void setLatitutdeDirection(Direction latitutdeDirection) {
        this.latitutdeDirection = latitutdeDirection;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Direction getLongitudeDirection() {
        return longitudeDirection;
    }

    public void setLongitudeDirection(Direction longitudeDirection) {
        this.longitudeDirection = longitudeDirection;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getVz() {
        return vz;
    }

    public void setVz(float vz) {
        this.vz = vz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fireball)) return false;
        Fireball fireball = (Fireball) o;
        return Float.compare(fireball.getEnergy(), getEnergy()) == 0 &&
                Float.compare(fireball.getTotalImpactEnergy(), getTotalImpactEnergy()) == 0 &&
                Float.compare(fireball.getLatitude(), getLatitude()) == 0 &&
                Float.compare(fireball.getLongitude(), getLongitude()) == 0 &&
                Float.compare(fireball.getAltitude(), getAltitude()) == 0 &&
                Float.compare(fireball.getVelocity(), getVelocity()) == 0 &&
                Float.compare(fireball.getVx(), getVx()) == 0 &&
                Float.compare(fireball.getVy(), getVy()) == 0 &&
                Float.compare(fireball.getVz(), getVz()) == 0 &&
                getDate().equals(fireball.getDate()) &&
                getLatitutdeDirection() == fireball.getLatitutdeDirection() &&
                getLongitudeDirection() == fireball.getLongitudeDirection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getEnergy(), getTotalImpactEnergy(), getLatitude(), getLatitutdeDirection(), getLongitude(), getLongitudeDirection(), getAltitude(), getVelocity(), getVx(), getVy(), getVz());
    }
}