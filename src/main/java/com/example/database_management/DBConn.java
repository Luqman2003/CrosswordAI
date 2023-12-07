package com.example.database_management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    public static Connection openConnection() throws SQLException {
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        if (databaseUrl == null || databaseUrl.isEmpty()) {
            throw new SQLException("Database URL not set in environment variables");
        }

        Connection conn = DriverManager.getConnection(databaseUrl);

        conn.setAutoCommit(true);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        return conn;
    }

    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = openConnection();
        QueryAbstract q = new Query();
        q.clearTables();
        q.createUser("luqman", "June142003*");
    }
}
