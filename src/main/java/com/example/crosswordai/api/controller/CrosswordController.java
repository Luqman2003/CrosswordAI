package com.example.crosswordai.api.controller;

import com.example.crosswordai.api.model.User;
import com.example.crosswordai.service.CrosswordService;
import com.example.crosswordai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrosswordController {

    private final CrosswordService crosswordService;

    @Autowired
    public CrosswordController(CrosswordService crosswordService) {
        this.crosswordService = crosswordService;
    }

    @PostMapping("/createCrossword")
    public ResponseEntity<String> createCrossword(@RequestParam String topic) {
        return null;
    }
}
