package com.shiftf6.workshop2.refactoring.c_exposing_your_privates;

import static com.shiftf6.workshop2.refactoring.c_exposing_your_privates.ImmutablePrice.price;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.JdbcTemplate;
import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.Price;
import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.PriceRepository;
import com.shiftf6.workshop2.refactoring.c_exposing_your_privates.auction.BidRepository;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InternalImplementationExposedTest {

    /*
    Having mutable objects really is a drag. Here we've got a very typical database repository, and even though it
    compiles, the test doesn't work.
    Can we fix this, so we can't write this in a broken way?
     */
    @Test
    public void youShouldNotShowOffYourPrivates() throws JdbcTemplate.DataAccessException {
        BidRepository bidRepository = new BidRepository();
        PriceRepository priceRepository = new PriceRepository();
        priceRepository.store(price(UUID.randomUUID(), "12345678", 11.0, LocalDateTime.now().minusDays(1)));
        priceRepository.copyAllOldPricesToBackupTable();
        priceRepository.truncateTable("prices");
        Price price = new Price();
        price.setUpc("12345678");
        price.setValue(10.45);
        price.setValidFrom(LocalDateTime.now());
        priceRepository.store(price);
        List<Price> prices = priceRepository.priceFor("12345678");
        assertThat(prices.size(), is(1));
    }

}
