package com.example.crosswordai.algorithm;

import java.util.ArrayList;
import java.util.List;

public class CrosswordAlgorithm {

  List<String> words;

  public CrosswordAlgorithm(List<String> words) {
    this.words = words;
  }

  /**
   * Creates a crossword using the list of words, words, field
   *
   * @return - a 2d char matrix which will represent our crossword
   */
  public char[][] generate() {
    // TODO: implement this
    String word = words.get(0);
    words.remove(word);

    return null;
  }

  /**
   *
   * @param word      - The word we want to place in the crossword
   * @param crossword - The crossword matrix in its current state
   * @param row       - The row index that contains same char as one in word
   * @param col       - The col index that contains same char as one in word
   * @return - returns true if we can place the word in the crossword and false
   *         otherwise
   */
  public boolean canPlace(String word, char[][] crossword, int row, int col) {
    // TODO: implement this
    return false;
  }

  public void place(String word, char[][] crossword, int row, int col, String direction) {
    // TODO: implement this
  }

}
