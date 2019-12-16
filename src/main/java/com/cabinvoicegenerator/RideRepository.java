package com.cabinvoicegenerator;

import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Rides>> userRides ;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Rides[] rides) {
        this.userRides.put(userId, new ArrayList<Rides>(Arrays.asList(rides)));
    }

    public Rides[] getRides(String userId) throws InvoiceServiceException {
        try {
            return this.userRides.get(userId).toArray(new Rides[0]);
        }catch (NullPointerException e){
            throw new InvoiceServiceException(e.getMessage(),InvoiceServiceException.ExceptionType.NO_DATA_ADDED);
        }

    }
}
