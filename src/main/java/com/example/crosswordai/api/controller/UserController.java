package com.example.crosswordai.api.controller;

import com.example.crosswordai.api.model.User;
import com.example.crosswordai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        int result = userService.login(user.getUsername(), user.getPassword());
        switch (result) {
            case -1:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error logging in. Try again");
            case 0:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");
            case 1:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password");
            case 2:
                return ResponseEntity.ok("Successful login!");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error");
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean created = userService.addUser(user.getUsername(), user.getPassword());
        if (created) {
            return ResponseEntity.ok("Successful creation!");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Creation failed. Username exists already or an error occurred");
    }
}
