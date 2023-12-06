package com.example.database_management;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query extends QueryAbstract {


    private static final String DELETE_USERS = "DELETE FROM Users";
    private PreparedStatement deleteUsersStmt;

    private static final String DELETE_FRIENDS = "DELETE FROM Friends";
    private PreparedStatement deleteFriendsStmt;

    private static final String SELECT_USER = "SELECT * FROM Users WHERE Username = ? AND Password = ?;";
    private PreparedStatement userStmt;

    private static final String SELECT_USERNAME = "SELECT * FROM Users WHERE Username = ?;";
    private PreparedStatement usernameStmt;

    private static final String INSERT_USER = "INSERT INTO Users (Username, Password) VALUES (?, ?);";
    private PreparedStatement insertUserStmt;

    private static final String SELECT_PASSWORD = "SELECT Password FROM Users WHERE Username = ?;";
    private PreparedStatement passwordStmt;

    public Query() throws SQLException, IOException {
        prepareStatements();
    }

    public void clearTables() {
        try {
            deleteUsersStmt.executeUpdate();
            deleteFriendsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatements() throws SQLException {
        userStmt = conn.prepareStatement(SELECT_USER);
        insertUserStmt = conn.prepareStatement(INSERT_USER);
        usernameStmt = conn.prepareStatement(SELECT_USERNAME);
        passwordStmt = conn.prepareStatement(SELECT_PASSWORD);
        deleteUsersStmt = conn.prepareStatement(DELETE_USERS);
        deleteFriendsStmt = conn.prepareStatement(DELETE_FRIENDS);
    }

    /* see java.QueryAbstract for spec */
    public boolean createUser(String username, String password) {
        try {
            // check if the username exists in the database already
            usernameStmt.clearParameters();
            usernameStmt.setString(1, username);
            ResultSet res = usernameStmt.executeQuery();
            boolean exists = false;
            while (res.next()) {
                exists = true;
            }
            res.close();
            if (exists) {
                return false;
            }

            insertUserStmt.clearParameters();
            byte[] hash = PasswordUtils.saltAndHashPassword(password);
            insertUserStmt.setString(1, username);
            insertUserStmt.setBytes(2, hash);
            insertUserStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* see java.QueryAbstract for spec */
    public int login(String username, String password) {
        try {
            usernameStmt.clearParameters();
            usernameStmt.setString(1, username);
            ResultSet res = usernameStmt.executeQuery();
            boolean doesntExist = true;
            while (res.next()) {
                doesntExist = false;
            }
            res.close();

            if (doesntExist) {
                System.out.println("User doesn't exist");
                return 0;
            }

            passwordStmt.clearParameters();
            passwordStmt.setString(1, username);
            ResultSet res2 = passwordStmt.executeQuery();
            byte[] hashedPassword = {};
            while (res2.next()) {
                hashedPassword = res2.getBytes("Password");
            }
            res2.close();
            if (hashedPassword.length == 0) { // this means we didn't enter the while loop, and we weren't able to retrieve the password
                throw new SQLException("No password was found given this username: " + username);
            }

            if (!PasswordUtils.plaintextMatchesSaltedHash(password, hashedPassword)) {
                System.out.println("Incorrect password");
                return 1;
            }
            return 2;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /* see java.QueryAbstract for spec */
    public boolean logout(String username, String password) {
        return false;
    }

    /* see java.QueryAbstract for spec */
    public boolean addFriend(String username1, String username2) {
        return false;
    }

    /* see java.QueryAbstract for spec */
    public void showFriends(String username1) {

    }
}
