package com.cabinvoicegenerator;

import java.util.List;

public class InvoiceService {
    private static final double NORMAL_MINIMUM_COST_PER_KILOMETER = 10;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final double NORMAL_MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time,TypesOfCabs types) {
        if (types.equals(TypesOfCabs.NORMAL_RIDES)) {
            double totalFare = (distance * NORMAL_MINIMUM_COST_PER_KILOMETER) + (time * NORMAL_COST_PER_MIN);
            return Math.max(totalFare, NORMAL_MINIMUM_FARE);
        }
        if (types.equals(TypesOfCabs.PREMIUM_RIDES)) {
            double totalFare = (distance * PREMIUM_MINIMUM_COST_PER_KILOMETER) + (time * PREMIUM_COST_PER_MIN);
            return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
        }
        return 0.0;
    }

    public InvoiceSummary calculateFare(List<Rides> rides) {
        double totalFare = 0;
        for (Rides ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time,ride.rides);
    }
        return new InvoiceSummary(rides.size(), totalFare);
    }

    public void addRides(String userId, Rides[] rides) {
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
