package com.cabinvoicegenerator;

public class Rides {

    public CabRides rides;
    public double time;
    public double distance;

    public Rides(double distance, double time, CabRides rides) {
        this.distance = distance;
        this.time = time;
        this.rides = rides;
    }
}
