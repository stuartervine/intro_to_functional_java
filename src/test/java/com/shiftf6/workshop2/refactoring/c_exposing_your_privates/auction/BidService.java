package com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction;

import java.util.UUID;

public class BidService {
    private final BidRepository repo;

    public BidService(BidRepository repo) {
        this.repo = repo;
    }

    public void makeBid(Bid bid) throws BidException {
        Bid currentBid = repo.getCurrentBidForItem(bid.itemId);
        if(currentBid == null) {
            repo.store(bid);
        } else {
            if(currentBid.value >= bid.value) {
                throw new BidException("Your bid is less than the current bid");
            } else {
                repo.markAllBidsAsNotWinning(bid.itemId);
                repo.store(bid);
                repo.markBidAsWinning(bid);
            }
        }
    }

    public double currentBidPrice(UUID itemId) {
        return repo.getCurrentBidForItem(itemId).value;
    }
}
