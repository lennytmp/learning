package matrix;

public class MatrixMap {
  /**
   * Prints the map on the screen.
   * @param map The map to print.
   */
  public static void print(int[][] map) {
    for (int[] row : map) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
    System.out.println();
  }


  /**
   * Prints the map on the screen.
   * @param map The map to print.
   */
  public static void print(char[][] map) {
    for (char[] row : map) {
      for (char value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
