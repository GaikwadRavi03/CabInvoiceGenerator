package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_TotalFareForNormalRide() {
        double distance = 2.0;
        int time = 5;
        InvoiceService invoiceGenerator = new InvoiceService();
        double fare = invoiceGenerator.calculateFare(distance, time,TypesOfCabs.NORMAL_RIDES);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForNormalRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService();
        double fare = invoiceGenerator.calculateFare(distance, time,TypesOfCabs.NORMAL_RIDES);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        Rides[] rides = {new Rides(2.0, 5, TypesOfCabs.NORMAL_RIDES),
                new Rides(0.1, 1, TypesOfCabs.NORMAL_RIDES)};
        List list = new ArrayList(Arrays.asList(rides));
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(list);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        String userId = "a@b.com";
        Rides[] rides = {new Rides(2.0, 5, TypesOfCabs.NORMAL_RIDES),
                new Rides(0.1, 1, TypesOfCabs.NORMAL_RIDES)
        };
        cabInvoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = null;
        try {
            summary = cabInvoiceGenerator.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
        }
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_TotalFareForPremiumRide() {
        double distance = 2.0;
        int time = 5;
        InvoiceService invoiceGenerator = new InvoiceService();
        double fare = invoiceGenerator.calculateFare(distance, time,TypesOfCabs.PREMIUM_RIDES);
        Assert.assertEquals(40, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForPremiumRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService();
        double fare = invoiceGenerator.calculateFare(distance, time,TypesOfCabs.PREMIUM_RIDES);
        Assert.assertEquals(20, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        Rides[] rides = {new Rides(2.0, 5, TypesOfCabs.PREMIUM_RIDES),
                new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)};
        List list = new ArrayList(Arrays.asList(rides));
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(list);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService();
        String userId = "a@b.com";
        Rides[] rides = {new Rides(2.0, 5, TypesOfCabs.PREMIUM_RIDES),
                new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)
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
        Rides[] rides = {new Rides(2.0, 5,TypesOfCabs.PREMIUM_RIDES),
                new Rides(0.1, 1,TypesOfCabs.NORMAL_RIDES)
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
        Rides[] rides1 = {new Rides(1.0, 10, TypesOfCabs.NORMAL_RIDES),
                new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)
        };
        Rides[] rides2 = {new Rides(3.0, 5, TypesOfCabs.NORMAL_RIDES),
                new Rides(0.1, 1, TypesOfCabs.PREMIUM_RIDES)
        };
        Rides[] rides3 = {new Rides(2.0, 5, TypesOfCabs.PREMIUM_RIDES),
                new Rides(0.1, 1, TypesOfCabs.NORMAL_RIDES)
        };
        ride.addRides(userId, rides1);
        ride.addRides(userId, rides2);
        ride.addRides(userId, rides3);

        InvoiceSummary summary = null;
        try {
            summary = ride.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
        }
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(6, 140);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }
}
