package com.shiftf6.workshop2.refactoring.a_mutability_is_bad.prices;

import static com.googlecode.totallylazy.Sequences.sequence;

import com.googlecode.totallylazy.functions.Function1;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class JdbcTemplate {
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static JdbcTemplate createJdbcTemplate() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return new JdbcTemplate(dataSource);
    }

    public boolean execute(String sql) throws DataAccessException {
        try {
            return connection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private Connection connection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(true);
        return connection;
    }

    public <T> List<T> queryForObject(String sql, String[] parameters, Function1<ResultSet, T> rowMapper) throws DataAccessException {
        try {
            List<T> ts = new ArrayList<T>();
            ResultSet resultSet = sequence(parameters).zipWithIndex().fold(connection().prepareStatement(sql), (ps, parameterWithIndex) -> {
                ps.setObject(parameterWithIndex.first().intValue()+1, parameterWithIndex.second());
                return ps;
            }).executeQuery();
            while(resultSet.next()) {
                ts.add(rowMapper.apply(resultSet));
            }
            return ts;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(String sql, Object[] parameters) throws DataAccessException {
        try {
            sequence(parameters).zipWithIndex().fold(connection().prepareStatement(sql), (ps, parameterWithIndex) -> {
                ps.setObject(parameterWithIndex.first().intValue()+1, parameterWithIndex.second());
                return ps;
            }).execute();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }

    }

    public class DataAccessException extends Exception {
        public DataAccessException(Exception e) {
            super(e);
        }
    }
}
