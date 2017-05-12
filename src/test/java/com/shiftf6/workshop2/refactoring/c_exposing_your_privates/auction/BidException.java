package com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction;

public class BidException extends Exception {
    public BidException(String message) {
        super(message);
    }
}
