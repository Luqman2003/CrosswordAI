package com.example.crosswordai.algorithm;

import java.util.List;

public class CrosswordAlgorithm {

  List<String> words;
  int maxWords;

  public CrosswordAlgorithm(List<String> words) {
    maxWords = 10;
    this.words = words;
  }

  /**
   * Creates a crossword using the list of words, words, field
   *
   * @return - a 2d char matrix which will represent our crossword
   */
  public char[][] generate() {
    String word = words.get(0);

    // place the first word in the list inside the 2d array ACROSS
    char[][] crossword = new char[1][word.length()];
    for (int i = 0; i < word.length(); i++) {
      crossword[0][i] = word.charAt(i);
    }

    // start the iterative approach to generating a crossword
    int count = 0;
    words.remove(word);
    while (!words.isEmpty() || count < maxWords) {
      String currWord = words.get(count);

      for (int i = 0; i < crossword.length; i++) {
        for (int j = 0; j < crossword[i].length; j++) {

          // iterate through the current word and check if there
          // exists a character in the word that also exists in the crossword
          for (int k = 0; k < currWord.length(); k++) {

            // checking for the same character
            if (currWord.charAt(k) == crossword[i][j]) {

              // now, we check if we can place that word in the horizontal direction
              if (canPlace(currWord, crossword, i, j, 0)) {

                // actually place the word in the crossword
                crossword = place(currWord, crossword, i, j, 0);

                // same check but for vertical direction
              } else if (canPlace(currWord, crossword, i, j, 1)) {

                // placing the word in the crossword
                crossword = place(currWord, crossword, i, j, 1);
              } else {
                // we can't place the word on this current iteration
                // figure out what we do in this situation
              }
            }
          }

        }
      }
      words.remove(currWord);
      count++;
    }

    return crossword;
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
  public boolean canPlace(String word, char[][] crossword, int row, int col, int direction) {
    // first thing to do is get the index of the character that exists in the word
    return true;
  }

  /**
   *
   * @param word
   * @param crossword
   * @param row
   * @param col
   * @param direction
   * @return
   */
  public char[][] place(String word, char[][] crossword, int row, int col, int direction) {
    // TODO: implement this
    return null;
  }

  /**
   *
   * @param crossword
   * @param direction
   * @param scale
   * @return
   */
  private char[][] expandCrossword(char[][] crossword, int direction, int scale) {
    return null;
  }

}
