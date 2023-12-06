package com.example.database_management;

import java.io.IOException;
import java.sql.*;

public abstract class QueryAbstract {
    // DB Connection
    protected Connection conn;

    private static final String TRANCOUNT_SQL = "SELECT @@TRANCOUNT AS tran_count";
    private PreparedStatement tranCountStatement;

    protected QueryAbstract() throws SQLException, IOException {
        this.conn = DBConn.openConnection();
        tranCountStatement = conn.prepareStatement(TRANCOUNT_SQL);
    }

    public final void clearTablesWrap() {
        try {
            clearTables();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract void clearTables() throws SQLException;

    /**
     * getter method for connection object
     */
    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public final int abstract_login(String username, String password) {
        try {
            return login(username, password);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract int login(String username, String password);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public final boolean abstract_logout(String username, String password) {
        try {
            return logout(username, password);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract boolean logout(String username, String password);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public final boolean abstract_createUser(String username, String password) {
        try {
            return createUser(username, password);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract boolean createUser(String username, String password);

    /**
     *
     * @param username1
     * @param username2
     * @return
     */
    public final boolean abstract_addFriend(String username1, String username2) {
        try {
            return addFriend(username1, username2);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract boolean addFriend(String username1, String username2);

    /**
     *
     * @param username1
     */
    public final void abstract_showFriends(String username1) {
        try {
            showFriends(username1);
        } finally {
            checkDanglingTransaction();
        }
    }

    public abstract void showFriends(String username1);

    /**
     * Throw IllegalStateException if transaction not completely complete, rollback.
     *
     */
    protected void checkDanglingTransaction() throws IllegalStateException {
        try {
            try (ResultSet rs = tranCountStatement.executeQuery()) {
                rs.next();
                int count = rs.getInt("tran_count");
                if (count > 0) {
                    throw new IllegalStateException(
                            "\nTransaction not fully commited/rolledback. Number of transactions currently"
                                    + " in process: " + count
                                    + "\nImportant: transactions must committed or rolledback before returning from"
                                    + " a method.  Example: flight is full; you must conn.rollback() before returning"
                                    + " the error string.\n");
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Database error", e);
        }
    }
}

