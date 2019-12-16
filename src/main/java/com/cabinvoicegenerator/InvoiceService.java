package com.cabinvoicegenerator;

public class InvoiceService {
    private static final double NORMAL_MINIMUM_COST_PER_KILOMETER = 10;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final double NORMAL_MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    private RideRepository rideRepository;
    private TypesOfCabs types;

    public InvoiceService(TypesOfCabs types) {
        this.rideRepository = new RideRepository();
        this.types = types;
    }

    public double calculateFare(double distance, int time) {
        if (this.types.equals(TypesOfCabs.NORMAL_RIDES)) {
            double totalFare = (distance * NORMAL_MINIMUM_COST_PER_KILOMETER) + (time * NORMAL_COST_PER_MIN);
            return Math.max(totalFare, NORMAL_MINIMUM_FARE);
        } else if (this.types.equals(TypesOfCabs.PREMIUM_RIDES)) {
            double totalFare = (distance * PREMIUM_MINIMUM_COST_PER_KILOMETER) + (time * PREMIUM_COST_PER_MIN);
            return Math.max(totalFare, PREMIUM_MINIMUM_FARE);
        }
        return 0.0;
    }

    public InvoiceSummary calculateFare(Rides[] rides) {
        double totalFare = 0;
        for (Rides ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Rides[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
