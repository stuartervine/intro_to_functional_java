package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

import static com.shiftf6.workshop2.refactoring.c_databases_get_involved.InternalImplementationExposedTest.ImmutablePrice.price;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class InternalImplementationExposedTest {

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
    public void youShouldNotShowOffYourPrivates() throws JdbcTemplate.DataAccessException {
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

    public static class ImmutablePrice implements PersistablePrice {

        private final UUID id;
        private final String upc;
        private final double price;
        private final LocalDateTime validFrom;

        public ImmutablePrice(UUID id, String upc, double price, LocalDateTime validFrom) {
            this.id = id;
            this.upc = upc;
            this.price = price;
            this.validFrom = validFrom;
        }

        public static ImmutablePrice price(UUID id, String upc, double price, LocalDateTime validFrom) {
            return new ImmutablePrice(id, upc, price, validFrom);
        }

        @Override
        public UUID getId() {
            return id;
        }

        @Override
        public String getUpc() {
            return upc;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public LocalDateTime getValidFrom() {
            return validFrom;
        }
    }
}
