package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

import java.time.LocalDateTime;
import java.util.UUID;

public class Price implements PersistablePrice {
    private String upc;
    private LocalDateTime validFrom;
    private double value;
    private UUID id;

    @Override
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public double getPrice() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
