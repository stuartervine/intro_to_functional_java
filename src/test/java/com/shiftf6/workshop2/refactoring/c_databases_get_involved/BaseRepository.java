package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

public class BaseRepository<T> {
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
