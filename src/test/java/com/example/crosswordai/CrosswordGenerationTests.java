package com.example.crosswordai;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.crosswordai.algorithm.CrosswordAlgorithm;

public class CrosswordGenerationTests {

  @Test
  public void testGenerate() {
    List<String> words = new ArrayList<>();
    words.add("hello");
    words.add("world");
    words.add("test");
    words.add("crossword");
    words.add("project");
    words.add("program");
    words.add("software");
    words.add("engineering");

    CrosswordAlgorithm alg = new CrosswordAlgorithm(words);
    // char[][] crossword = alg.generate();
    // assertEquals()
  }
}
