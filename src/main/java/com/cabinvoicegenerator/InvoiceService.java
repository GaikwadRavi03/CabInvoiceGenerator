package com.cabinvoicegenerator;

import java.util.List;

public class InvoiceService {

    private CabRides type;
    private double distance;
    private double time;

    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public InvoiceService(double distance, double time, CabRides type) {
        this.rideRepository = new RideRepository();
        this.distance = distance;
        this.time = time;
        this.type = type;
    }

    public double calculateFare(Ride ride) {
        double fare = ride.rides.calcCostOfCabRide(ride);
        return fare;
    }

    public double calculateFare() {
        Ride ride = new Ride(distance, time, type);
        double fare = ride.rides.calcCostOfCabRide(ride);
        return fare;
    }

    public InvoiceSummary calculateFare(List<Ride> rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride);
        }
        return new InvoiceSummary(rides.size(), totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) throws InvoiceServiceException {
        try {
            return this.calculateFare(rideRepository.getRides(userId));
        } catch (InvoiceServiceException e) {
            throw new InvoiceServiceException(e.getMessage(), InvoiceServiceException.ExceptionType.NO_DATA_ADDED);
        }
    }
}
