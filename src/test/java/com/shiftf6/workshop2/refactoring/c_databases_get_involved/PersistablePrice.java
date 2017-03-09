package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PersistablePrice {
    LocalDateTime getValidFrom();

    double getPrice();

    String getUpc();

    UUID getId();
}
