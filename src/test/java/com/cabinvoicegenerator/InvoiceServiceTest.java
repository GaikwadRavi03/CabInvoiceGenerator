package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_TotalFareForNormalRide() {
        try {
            double distance = 2.0;
            int time = 5;
            InvoiceService invoiceGenerator = new InvoiceService(distance, time, CabRides.NORMAL_RIDES);
            double fare = invoiceGenerator.calculateFare();
            Assert.assertEquals(25, fare, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForNormalRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService(distance, time, CabRides.NORMAL_RIDES);
        double fare = invoiceGenerator.calculateFare();
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        Ride[] rides = {new Ride(2.0, 5, CabRides.NORMAL_RIDES),
                new Ride(0.1, 1, CabRides.NORMAL_RIDES)};
        List list = new ArrayList(Arrays.asList(rides));
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(list);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, CabRides.NORMAL_RIDES),
                new Ride(0.1, 1, CabRides.NORMAL_RIDES)
        };
        try {
            cabInvoiceGenerator.addRides(userId, rides);
            InvoiceSummary summary = cabInvoiceGenerator.getInvoiceSummary(userId);
            System.out.println(summary);
            InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
            System.out.println(expectedInvoiceSummery);
            Assert.assertEquals(expectedInvoiceSummery, summary);
        } catch (InvoiceServiceException e) {
        }
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_TotalFareForPremiumRide() {
        double distance = 2.0;
        int time = 5;
        InvoiceService invoiceGenerator = new InvoiceService(distance, time, CabRides.PREMIUM_RIDES);
        double fare = invoiceGenerator.calculateFare();
        Assert.assertEquals(40, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForPremiumRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService(distance, time, CabRides.PREMIUM_RIDES);
        double fare = invoiceGenerator.calculateFare();
        Assert.assertEquals(20, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        Ride[] rides = {new Ride(2.0, 5, CabRides.PREMIUM_RIDES),
                new Ride(0.1, 1, CabRides.PREMIUM_RIDES)};
        List list = new ArrayList(Arrays.asList(rides));
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(list);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, CabRides.PREMIUM_RIDES),
                new Ride(0.1, 1, CabRides.PREMIUM_RIDES)
        };
        cabInvoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = null;
        try {
            summary = cabInvoiceGenerator.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
        }
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdRidesMultipleTypesOfCabs_ShouldReturnInvoiceSummaryForPremiumRide() {
        InvoiceService ride = new InvoiceService();

        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, CabRides.PREMIUM_RIDES),
                new Ride(0.1, 1, CabRides.NORMAL_RIDES)
        };
        ride.addRides(userId, rides);
        InvoiceSummary summary = null;
        try {
            summary = ride.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
        }
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 45);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdRidesMultipleTypesOfCabs_ShouldReturnTotalFareInvoiceSummaryForAllRides() {
        InvoiceService ride = new InvoiceService();
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
        ride.addRides(userId, ride1);
        ride.addRides(userId, ride2);
        ride.addRides(userId, ride3);

        InvoiceSummary summary = null;
        try {
            summary = ride.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
        }
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(6, 140);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }
}
