package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void givenMessage_AddRidesByParticularUserId_ShouldReturnExactUserIdInvoiceSummary() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "a@b.com";
            Rides[] rides = {new Rides(2.0, 5),
                    new Rides(0.1, 1)
            };
            rideRepository.addRides(userId, rides);
            Rides[] summary = new Rides[0];
            summary = rideRepository.getRides(userId);
            Assert.assertEquals(rides[0], summary[0]);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenMessage_RidesNotAdded_ShouldReturnCustomException() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "a@b.com";
            Rides[] summary = rideRepository.getRides(userId);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.NO_DATA_ADDED, e.type);
        }
    }
}
