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
            Ride[] rides = {new Ride(2.0, 5, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.NORMAL_RIDES)
            };
            rideRepository.addRides(userId, rides);
            List<Ride> summary = rideRepository.getRides(userId);
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
            Ride[] ride1 = {new Ride(1.0, 10, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] ride2 = {new Ride(3.0, 5, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] ride3 = {new Ride(2.0, 5, CabRides.PREMIUM_RIDES),
                    new Ride(0.1, 1, CabRides.NORMAL_RIDES)
            };
            rideRepository.addRides(userId, ride1);
            rideRepository.addRides(userId, ride2);
            rideRepository.addRides(userId, ride3);
            List<Ride> rideList = rideRepository.getRides(userId);
            Assert.assertEquals(6, rideList.size());
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenMessage_WhenMultipleUsersAddedRides_ShouldReturnUsersInvoiceSummary() {
        try {
            RideRepository rideRepository = new RideRepository();
            String userId1 = "a@b.com";
            Ride[] ride1 = {new Ride(1.0, 10, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] ride2 = {new Ride(3.0, 5, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] ride3 = {new Ride(2.0, 5, CabRides.PREMIUM_RIDES),
                    new Ride(0.1, 1, CabRides.NORMAL_RIDES)
            };
            String userId2 = "x@z.com";
            Ride[] user2Ride1 = {new Ride(1.0, 10, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] user2Ride2 = {new Ride(3.0, 5, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            Ride[] user2Ride3 = {new Ride(2.0, 5, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.NORMAL_RIDES),
                    new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
            };
            rideRepository.addRides(userId1, ride1);
            rideRepository.addRides(userId1, ride2);
            rideRepository.addRides(userId1, ride3);
            rideRepository.addRides(userId2, user2Ride1);
            rideRepository.addRides(userId2, user2Ride2);
            rideRepository.addRides(userId2, user2Ride3);

            List<Ride> rideList = rideRepository.getRides(userId2);
            Assert.assertEquals(7, rideList.size());
        } catch (InvoiceServiceException e) {
        }
    }
}
