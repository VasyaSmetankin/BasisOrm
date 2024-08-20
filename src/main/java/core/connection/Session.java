package core.connection;


import core.querybuilder.QueryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("checkstyle:Indentation")
public class Session {
    QueryBuilder queryBuilder;
    DatabaseManager configuration;
    Connection connection;

    public Session() {
        this.configuration = new DatabaseManager();
        this.queryBuilder = new QueryBuilder();
    }

    public void startConnection(int transactionLevel) {
        try {
            connection = DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(transactionLevel);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Transaction error: " + e.getMessage(), e);
        }
    }

    public void create(Object object) {
        startConnection(1);
        String query = queryBuilder.create(object);
        try {
            connection.createStatement().execute(query);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Transaction error: " + e.getMessage(), e);
        }
    }

    public void update(Object object) {
        startConnection(1);
        String query = queryBuilder.update(object);
        try {
            connection.createStatement().execute(query);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Transaction error: " + e.getMessage(), e);
        }
    }

    public void delete(Object object) {
        startConnection(1);
        String query = queryBuilder.delete(object);
        try {
            connection.createStatement().execute(query);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Transaction error: " + e.getMessage(), e);
        }
    }

    public void read(Object object) {
        startConnection(1);
        String query = queryBuilder.read(object);
        try {
            connection.createStatement().execute(query);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Transaction error: " + e.getMessage(), e);
        }
    }


    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
