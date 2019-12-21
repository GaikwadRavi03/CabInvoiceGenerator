package com.cabinvoicegenerator;

public class Ride {

    public CabRides rides;
    public double time;
    public double distance;

    public Ride(double distance, double time, CabRides rides) {
        this.distance = distance;
        this.time = time;
        this.rides = rides;
    }
}
