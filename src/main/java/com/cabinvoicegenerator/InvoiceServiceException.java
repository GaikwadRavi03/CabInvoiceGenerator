package com.cabinvoicegenerator;

public class InvoiceServiceException extends Exception {
    enum ExceptionType {
        FILE_PROBLEM, NO_DATA_ADDED
    }

    ExceptionType type;

    public InvoiceServiceException(String s, ExceptionType type) {
        super(s);
        this.type = type;
    }
}
