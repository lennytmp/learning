/**
 * Implements the algorithm to spread water in a labyrinth.
 */

package matrix;


class WaterSpillage {


  /**
   * Sets the map and spills water.
   * @param args Program arguments, not used.
   */
  public static void main(String[] args) {
    int[][] map = {
      {1, 1, 1, 1, 1, 1},
      {1, 0, 1, 0, 1, 1},
      {1, 0, 1, 1, 1, 1},
      {1, 0, 0, 1, 0, 1},
      {1, 0, 1, 0, 0, 1},
      {1, 1, 1, 1, 1, 1},
    };
    printMap(map);
    spillWater(map, new Point(1,1));
    printMap(map);
  }


  /**
   * Spills the water in the labyrinth.
   * @param lab The labyrinth where it happens.
   * @param p The point where the flood starts.
   */
  public static void spillWater(int[][] lab, Point p) {
    if (p.x < 0 || p.y < 0 ||
        p.x >= lab.length || p.y >= lab.length ||
        lab[p.x][p.y] != 0) {
      return;
    }
    lab[p.x][p.y] = 2;
    for (int x = p.x - 1; x <= p.x + 1; x++) {
        for (int y = p.y -1; y <= p.y + 1; y++) {
        if (p.x != x && p.y != y) { // remove diagonals
          continue;
        }
        spillWater(lab, new Point(x, y));
      }
    }    
  }


  /**
   * Prints the map on the screen.
   * @param map The map to print.
   */
  public static void printMap(int[][] map) {
    for (int[] row : map) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
