package com.shiftf6.workshop3.databases.a_hello_jdbc;

import static com.googlecode.totallylazy.numbers.Numbers.range;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeAConnectionTest {

    @Test
    public void howToMakeAConnectionToTheDatabase() throws SQLException {
        /*
        In this example we'll create a connection using the DriverManger.
        Typically in a real production application you'll use a different method, but we'll cover that later.

        H2 is a small in memory SQL database, you don't need to run a separate server for it,
        so we'll use it to explore the JDBC APIs. We'll run a Postgres server later on in a docker container
        when we start to look at transactions and locking strategies.
         */
        Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:test",
                "sa",
                ""
        );

        //explore the connection API.
//        connection.

        //do something with the connection, let's execute some SQL.
        boolean executionResult = connection.createStatement().execute("SELECT 1");
        assertThat(executionResult, is(true));
    }

    @Test
    public void whyIsTheAboveNotSuitableForProductionApps() throws SQLException {
        /*
        The above call creates a new connection to the SQL database. Creating connections
        is typically quite an expensive operation, and you don't want to do that every time you need
        to process something against the database.

        Can we share one connection for the application? Imagine the following scenario:
         */
        Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:test",
                "sa",
                ""
        );

        // execute some long running request
        newSingleThreadExecutor().execute(() -> longRunningSqlStatement(connection));

        // while that's running, try and use the connection...

        boolean execute = connection.createStatement().execute("SELECT 1");


    }

    private void longRunningSqlStatement(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            range(1, 1000000).forEach(i -> {
                try {
                    statement.addBatch("SELECT " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
