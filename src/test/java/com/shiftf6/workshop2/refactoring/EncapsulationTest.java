package com.shiftf6.workshop2.refactoring;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.List;

public class EncapsulationTest {

    public static class BaseRepository<T> {
        JdbcTemplate jdbcTemplate;

        public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }
    }

    public static class PriceRepository extends BaseRepository<Price> {
        public List<Price> priceFor(String upc) {
            ResultSet resultSet = this.jdbcTemplate.executeSql(format("SELECT * FROM prices WHERE upc = '%s';", upc));
            return null;
        }
    }

    public static class Price {
        private LocalDateTime validFrom;
        private double value;

        public LocalDateTime getValidFrom() {
            return validFrom;
        }

        public void setValidFrom(LocalDateTime validFrom) {
            this.validFrom = validFrom;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    public static class JdbcTemplate {
        public ResultSet executeSql(String sql) {
            return new ResultSet();
        }
    }

    public static class ResultSet {

    }

}
