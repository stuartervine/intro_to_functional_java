package com.shiftf6.workshop2.refactoring.a_mutability_is_bad;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.DatabaseMigrator;
import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.JdbcTemplate;
import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.Price;
import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.PriceRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class MutabilityNotRequiredTest {
    private JdbcTemplate jdbcTemplate = JdbcTemplate.createJdbcTemplate();

    @Before
    public void setupDatabase() throws JdbcTemplate.DataAccessException {
        new DatabaseMigrator(jdbcTemplate).migrateDatabase();
    }

    /*
    Having mutable objects really is a drag. Here we've got a very typical database repository, and even though it
    compiles, the test doesn't work.
    Can we fix this, so we can't write this in a broken way?
     */
    @Test
    public void whyHaveMutabilityLetsUseTheCompiler() throws JdbcTemplate.DataAccessException {
        PriceRepository priceRepository = new PriceRepository();
        Price price = new Price();
        price.setUpc("12345678");
        price.setValue(10.45);
        price.setValidFrom(LocalDateTime.now());
        priceRepository.store(price);
        List<Price> prices = priceRepository.priceFor("12345678");
        assertThat(prices.size(), is(1));
    }


}
