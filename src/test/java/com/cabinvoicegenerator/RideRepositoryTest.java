package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RideRepositoryTest {

    @Test
    public void whenAddRidesByParticularUserId_ShouldReturnExactUserIdInvoiceSummary() {
        RideRepository rideRepository = new RideRepository();
        String userId = "a@b.com";
        Rides[] rides = {new Rides(2.0, 5),
                new Rides(0.1, 1)
        };
        rideRepository.addRides(userId, rides);
        Rides[] summary = rideRepository.getRides(userId);
        Assert.assertEquals(rides[0],summary[0]);
    }
}
