package com.shiftf6.workshop2.refactoring.c_databases_get_involved;

import static java.lang.String.format;
import static java.time.ZoneOffset.UTC;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class PriceRepository extends BaseRepository<Price> {
    public List<Price> priceFor(String upc) {
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM prices WHERE upc = ?;", new String[]{upc}, (result) -> {
                Price price = new Price();
                price.setId(UUID.fromString(result.getString("id")));
                price.setUpc(result.getString("upc"));
                price.setValue(result.getDouble("price"));
                price.setValidFrom(result.getTimestamp("validFrom").toLocalDateTime());
                return price;
            });
        } catch (JdbcTemplate.DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void store(PersistablePrice price) {
        String insertSql = format("INSERT INTO prices (id, upc, price, validFrom) VALUES (?, ?, ?, ?)");
        try {
            jdbcTemplate.update(insertSql, new Object[]{
                    price.getId(),
                    price.getUpc(),
                    price.getPrice(),
                    Timestamp.from(price.getValidFrom().toInstant(UTC))
            });
        } catch (JdbcTemplate.DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void copyAllOldPricesToBackupTable() {

    }

    public void truncateTable(String tableName) {

    }
}
