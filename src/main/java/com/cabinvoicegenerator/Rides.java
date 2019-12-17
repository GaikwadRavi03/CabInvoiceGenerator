package com.cabinvoicegenerator;

public class Rides {

    public TypesOfCabs rides;
    public int time;
    public double distance;

    public Rides(double distance, int time, TypesOfCabs rides) {
        this.distance = distance;
        this.time = time;
        this.rides = rides;


    }
}
