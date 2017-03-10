package com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    public final UUID id;
    public final UUID itemId;
    public final String bidder;
    public final LocalDateTime bidTime;
    public final double value;

    private Bid(UUID id, UUID itemId, String bidder, LocalDateTime bidTime, double value) {
        this.id = id;
        this.itemId = itemId;
        this.bidder = bidder;
        this.bidTime = bidTime;
        this.value = value;
    }

    public static Bid bid(UUID id, UUID itemId, String bidder, LocalDateTime bidTime, double value) {
        return new Bid(id, itemId, bidder, bidTime, value);
    }
}
