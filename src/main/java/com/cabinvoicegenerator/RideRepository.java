package com.cabinvoicegenerator;

import java.util.*;

public class RideRepository {
    Map<String, List<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        List<Ride> rideList = new ArrayList<>(Arrays.asList(rides));
        List<Ride> list = this.userRides.get(userId);
        if (list != null) rideList.addAll(list);
        this.userRides.put(userId, rideList);
    }

    public List<Ride> getRides(String userId) throws InvoiceServiceException {
        try {
            return this.userRides.get(userId);
        } catch (NullPointerException e) {
            throw new InvoiceServiceException(e.getMessage(), InvoiceServiceException.ExceptionType.NO_DATA_ADDED);
        }
    }

}
