package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_TotalFareForNormalRide() {
        double distance = 2.0;
        int time = 5;
        InvoiceService invoiceGenerator = new InvoiceService(TypesOfCabs.NORMAL_RIDES);
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForNormalRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService(TypesOfCabs.NORMAL_RIDES);
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService(TypesOfCabs.NORMAL_RIDES);
        Rides[] rides = {new Rides(2.0, 5),
                new Rides(0.1, 1)};
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForNormalRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService(TypesOfCabs.NORMAL_RIDES);
        String userId = "a@b.com";
        Rides[] rides = {new Rides(2.0, 5),
                new Rides(0.1, 1)
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
        InvoiceService invoiceGenerator = new InvoiceService(TypesOfCabs.PREMIUM_RIDES);
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_GeneratorShouldReturn_MinimumFareForPremiumRide() {
        double distance = 0.1;
        int time = 1;
        InvoiceService invoiceGenerator = new InvoiceService(TypesOfCabs.PREMIUM_RIDES);
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(20, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturn_TotalFareForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService(TypesOfCabs.PREMIUM_RIDES);
        Rides[] rides = {new Rides(2.0, 5),
                new Rides(0.1, 1)};
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummery, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummaryForPremiumRide() {
        InvoiceService cabInvoiceGenerator = new InvoiceService(TypesOfCabs.PREMIUM_RIDES);
        String userId = "a@b.com";
        Rides[] rides = {new Rides(2.0, 5),
                new Rides(0.1, 1)
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

}
