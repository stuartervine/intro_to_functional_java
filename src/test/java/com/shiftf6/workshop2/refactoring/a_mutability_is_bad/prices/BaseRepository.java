package com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices;

public class BaseRepository<T> {
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
