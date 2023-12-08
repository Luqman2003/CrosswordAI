package com.example.crosswordai.service;

import org.springframework.stereotype.Service;

import com.example.crosswordai.algorithm.CrosswordAlgorithm;

@Service
public class CrosswordService {

  CrosswordAlgorithm alg;

  public CrosswordService() {
    // TODO: make openai api call to generate words
    // based off a topic and submit it as a parameter
    // for the crosswordalgorithm creation
    alg = new CrosswordAlgorithm(null);
  }

  public char[][] createCrossword() {
    return alg.generate();
  }
}
