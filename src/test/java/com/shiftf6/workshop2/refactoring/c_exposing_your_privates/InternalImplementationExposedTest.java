package com.shiftf6.workshop2.refactoring.c_exposing_your_privates;

import static com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction.Bid.bid;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.JdbcTemplate;
import com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction.BidException;
import com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction.BidRepository;
import com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction.BidService;
import org.junit.Test;

import java.util.UUID;

public class InternalImplementationExposedTest {

    /*
     */
    @Test
    public void youShouldNotShowOffYourPrivates() throws JdbcTemplate.DataAccessException, BidException {
        BidService bidService = new BidService(new BidRepository());
        UUID itemId = randomUUID();
        bidService.makeBid(bid(randomUUID(), itemId, "Bob", now(), 10));
        bidService.makeBid(bid(randomUUID(), itemId, "Fred", now(), 11));
        bidService.makeBid(bid(randomUUID(), itemId, "Bob", now(), 12));

        assertThat(bidService.currentBidPrice(itemId), is(12.0));
    }

}
