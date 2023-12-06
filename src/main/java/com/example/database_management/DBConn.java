package com.example.database_management;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {

    public static Connection openConnection() throws SQLException, IOException {
        Properties configProps = new Properties();
        configProps.load(new FileInputStream("dbconn.properties"));

        String serverURL = configProps.getProperty("crossword.server_url");
        String dbName = configProps.getProperty("crossword.database_name");
        String adminName = configProps.getProperty("crossword.username");
        String password = configProps.getProperty("crossword.password");

        String connectionUrl = String.format("jdbc:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
                serverURL, dbName, adminName, password);
        Connection conn = DriverManager.getConnection(connectionUrl);

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
