package com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction;

import static java.util.Comparator.comparingDouble;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BidRepository {

    private List<Bid> bids = new ArrayList<>();

    public void store(Bid bid) {
        bids.add(bid);
    }

    public void copyAllBidsToArchiveTable(UUID itemId) {

    }

    public void markAllBidsAsNotWinning(UUID itemId) {

    }

    public void markBidAsWinning(Bid bid) {

    }

    public List<Bid> getBidsForItem(UUID itemId) {
        return null;
    }

    public Bid getCurrentBidForItem(UUID itemId) {
        return bids.stream()
                .filter(bid -> bid.itemId.equals(itemId))
                .sorted(comparingDouble(bid -> -bid.value))
                .findFirst().orElse(null);
    }
}
