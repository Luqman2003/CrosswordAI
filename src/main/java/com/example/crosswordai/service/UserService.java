package com.example.crosswordai.service;

import com.example.database_management.Query;
import com.example.database_management.QueryAbstract;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    QueryAbstract q;


    public UserService() throws SQLException, IOException {
        q = new Query();
    }


    public boolean addUser(String username, String password) {
        return q.createUser(username, password);
    }

    public int login(String username, String password) {
         return q.login(username, password);
    }

}
