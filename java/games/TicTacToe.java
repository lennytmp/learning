/**
 * Implements the Tic Tac Toe game.
 */

package games;

import java.util.Random;

class TicTacToe {

  // The game field.
  char[][] field = new char[3][3];
  
  //  Size of the line to become a winner
  int winSize = 3;

  // If true, it's x turn to move. False - o's one.
  boolean xTurn = true;

  // Number of free cells left on the field.
  public int freeCells;

  // Random generator for random turns.
  Random generator = new Random();

  /**
   * Game constructor, intiliazes the field.
   */
  public TicTacToe() {
    freeCells = 0;
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        freeCells++;
        field[i][j] = ' ';
      }
    }
  }

  /**
   * Determines if there is a winner in the game.
   * @return space - no winner, x - x won, o - o won.
   */
  public char getWinner() {
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        if (field[i][j] == ' ') {
          continue;
        }
        String[] directions = {"hor", "ver", "dir", "dil"};
        for (String dir : directions) {
          if (isWinThere(j, i, dir)) {
            return field[i][j];
          }
        }
      }
    }
    return ' ';
  }

  /**
   * Checks the direction from a specific point for a winning
   * combination. 
   * @param x X coordinate. 
   * @param y Y coordinate. 
   * @param dir Direction to check: hor, ver, dir, dil.
   * @return True if the combination is found (there is a winner).
   */
  boolean isWinThere(int x, int y, String dir) {
    char symbol = field[y][x];
    int result = 0;
    while (y >= 0 && x >= 0 && y < field.length && x < field.length &&
          field[y][x] == symbol) {
      if (dir == "hor" || dir == "dir") {
        x++;
      }
      if (dir == "dil") {
        x--;
      }
      if (dir == "ver" || dir == "dir" || dir == "dil") {
        y++;
      }
      result++;
    }
    return result == winSize;
  }

  
  /**
   * Makes a random turn, switches current player. 
   */
  public void makeRandomTurn() {
    int a = 1 + generator.nextInt(freeCells);
    System.out.println(a + " out of " + freeCells);
    int counter = 0; 
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        if (field[i][j] != ' ') {
          continue;
        }
        counter++;
        if (counter == a) {
          if (xTurn) {
            field[i][j] = 'x';
            freeCells--;
          } else {
            field[i][j] = 'o';
            freeCells--;
          }
          xTurn = !xTurn;
          return;
        }
      }
    }
  }
    

  /**
   * Initialisis a game of TicTacToe, does random turns until
   * a winner is found or out of free space, prints the field.
   * @param args Array of arguments for launching the program.
   */
  public static void main(String[] args) {
    TicTacToe game = new TicTacToe();
    game.printField();
    while (game.freeCells > 0) {
      game.makeRandomTurn();
      game.printField();
      char winner = game.getWinner();
      if (winner != ' ') {
        System.out.println(winner + " has won!");
        return;
      }
      System.out.println();
    }
  }

  /**
   * Prints the field of Tic Tac Toe. 
   */
  public void printField() {
    for (char[] row : field) {
      for (char value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
}
