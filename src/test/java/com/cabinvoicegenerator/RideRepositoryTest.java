package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RideRepositoryTest {

    @Test
    public void givenMessage_AddRidesByParticularUserId_ShouldReturnExactUserIdInvoiceSummary() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "a@b.com";
            Rides[] rides = {new Rides(2.0, 5, TypesOfCabs.NORMAL_RIDES),
                    new Rides(0.1, 1, TypesOfCabs.NORMAL_RIDES)
            };
            rideRepository.addRides(userId, rides);
            List<Rides> summary = rideRepository.getRides(userId);
            Assert.assertEquals(2, summary.size());
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenMessage_RidesNotAdded_ShouldReturnCustomException() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "a@b.com";
            List summary = rideRepository.getRides(userId);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.NO_DATA_ADDED, e.type);
        }
    }

    @Test
    public void givenMessage_WhenUserIdsMultipleRidesAdded_ShouldReturnUsersInvoiceSummary() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId = "a@b.com";
            Rides[] rides1 = {new Rides(1.0, 10, TypesOfCabs.NORMAL_RIDES),
                    new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)
            };
            Rides[] rides2 = {new Rides(3.0, 5, TypesOfCabs.NORMAL_RIDES),
                    new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)
            };
            Rides[] rides3 = {new Rides(2.0, 5, TypesOfCabs.PREMIUM_RIDES),
                    new Rides(0.1, 1, TypesOfCabs.NORMAL_RIDES)
            };
            rideRepository.addRides(userId, rides1);
            rideRepository.addRides(userId, rides2);
            rideRepository.addRides(userId, rides3);
            List<Rides> ridesList = rideRepository.getRides(userId);
            Assert.assertEquals(6, ridesList.size());
        } catch (InvoiceServiceException e) {
        }
    }
}
