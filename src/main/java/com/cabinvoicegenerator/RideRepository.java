package com.cabinvoicegenerator;

import java.util.*;

public class RideRepository {
    Map<String, List<Rides>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Rides[] rides) {
        List<Rides> ridesList = new ArrayList<>(Arrays.asList(rides));
        List<Rides> list = this.userRides.get(userId);
        if (list != null) ridesList.addAll(list);
        this.userRides.put(userId, ridesList);
    }

    public List<Rides> getRides(String userId) throws InvoiceServiceException {
        try {
            return this.userRides.get(userId);
        } catch (NullPointerException e) {
            throw new InvoiceServiceException(e.getMessage(), InvoiceServiceException.ExceptionType.NO_DATA_ADDED);
        }
    }

}
