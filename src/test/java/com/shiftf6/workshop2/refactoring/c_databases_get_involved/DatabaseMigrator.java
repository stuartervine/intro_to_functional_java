package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

public class DatabaseMigrator {
    private JdbcTemplate jdbcTemplate;

    public DatabaseMigrator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void migrateDatabase() throws JdbcTemplate.DataAccessException {
        jdbcTemplate.execute("create table prices (\n" +
                "    id               uuid        primary key,\n" +
                "    upc              varchar     not null,\n" +
                "    validFrom        timestamp   not null,\n" +
                "    price            numeric     not null\n" +
                ");");
    }
}
