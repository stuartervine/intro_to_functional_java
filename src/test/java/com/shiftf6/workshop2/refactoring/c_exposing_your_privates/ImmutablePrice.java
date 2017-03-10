package com.shiftf6.workshop2.refactoring.c_exposing_your_privates;

import com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices.PersistablePrice;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImmutablePrice implements PersistablePrice {

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
