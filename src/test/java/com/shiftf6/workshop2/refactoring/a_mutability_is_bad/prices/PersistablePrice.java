package com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PersistablePrice {
    LocalDateTime getValidFrom();

    double getPrice();

    String getUpc();

    UUID getId();
}
