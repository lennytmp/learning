/**
 * Implementation of Tic Tac Toe game.
 */

package games;

import java.util.Random;

class TicTacToe {
  char[][] field = new char[3][3];
  boolean xTurn = true;
  Random generator = new Random();


  public TicTacToe() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        field[i][j] = ' ';
      }
    }
  }

  
  public void makeRandomTurn() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        int a = generator.nextInt(2);
        if (field[i][j] == ' ' && a == 1) {
          if (xTurn) {
            field[i][j] = 'x';
          } else {
            field[i][j] = 'o';
          }
          xTurn = !xTurn;
          return;
        }
      }
    }
  }
  

  public static void main(String[] args) {
    TicTacToe game = new TicTacToe();
    game.printField();
    for (int i = 1; i < 5; i++) {
      game.makeRandomTurn();
      System.out.println("Turn: " + i);
      game.printField();
    }
  }


  public void printField() {
    for (char[] row : field) {
      for (char value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
}
