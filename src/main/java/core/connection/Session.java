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
