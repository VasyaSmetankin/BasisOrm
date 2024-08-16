package core.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    DatabaseManager configuration;
    Connection connection;

    public Session() {
        this.configuration = new DatabaseManager();
    }

    public void beginTransaction() {
        try {
            connection = DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
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
        }
    }
}
