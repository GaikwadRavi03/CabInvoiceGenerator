package com.cabinvoicegenerator;

public class InvoiceSummary {
    public double averagePerRide;
    public double totalFare;
    public int numOfRides;

    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.averagePerRide = this.totalFare / this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.averagePerRide, averagePerRide) == 0 &&
                totalFare == that.totalFare &&
                Double.compare(that.numOfRides, numOfRides) == 0;
    }


}

