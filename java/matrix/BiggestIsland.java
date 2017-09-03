/**
 * Perfomrs search of the biggest "island" in a matrix.
 */

package matrix;
import java.util.ArrayList;
import java.util.Arrays;

//Class to implement the search for biggest island.
public class BiggestIsland {


  /**
   * Sets an the matrix, runs the algorithm and prints the result.
   * @param args Array of arguments for launching the program. Ignored.
   * @export
   */
  public static void main(String[] args) {
    int[][] matrix = {
      {0, 1, 1 , 1},
      {1, 0, 1 , 1},
      {1, 1, 0 , 1},
      {1, 0, 0 , 1}
    };
    MatrixMap.print(matrix);
    System.out.println(countBiggestIsland1(matrix));
    System.out.println(countBiggestIsland2(matrix));
  }


  /**
   * Counts the number points in the biggest island.
   * @param ocean The initial matrix to search in.
   * @return The number of points in the biggest island.
   */
  public static int countBiggestIsland2(int[][] ocean) {
    int len = ocean.length, maxSize = 0;
    int[][] visited = new int[len][len];
    for (int x = 0; x < len; x++) {
      for (int y = 0; y < len; y++) {
        int size = countIsland(x, y, ocean, visited);
        if (size > maxSize) {
          maxSize = size;
        }
      }
    }
    return maxSize;
  }


  /**
   * Gets the number of points in an island at x, y.
   * @param x The x coordinate in an ocean. 
   * @param y The y coordinate in an ocean. 
   * @param ocean The initial matrix to search in.
   * @param visited the matrix of same size as island, 1 if the point
   * was previously analysed, 0 if not.
   * @return The number of points in the biggest island.
   */
  public static int countIsland(int x, int y, int[][] ocean, int[][] visited) {
    if (x < 0 || y < 0 || x >= ocean.length || y >= ocean.length) {
      return 0;
    }
    if (visited[x][y] == 1) {
      return 0;
    }
    if (ocean[x][y] == 0) {
      return 0;
    }
    visited[x][y] = 1;
    return 1 + countIsland(x + 1, y, ocean, visited) +
      countIsland(x - 1, y, ocean, visited) +
      countIsland(x, y + 1, ocean, visited) +
      countIsland(x, y - 1, ocean, visited);
  }


  /**
   * Counts the number points in the biggest island.
   * @param ocean The initial matrix to search in.
   * @return The number of points in the biggest island.
   */
  public static int countBiggestIsland1(int[][] ocean) {
    //Getting each point in a seperate island.
    ArrayList<ArrayList<Point>> islands = new ArrayList<ArrayList<Point>>();
    for (int x = 0, len = ocean.length; x < len; x++) {
      for (int y = 0; y < len; y++) {
        if (ocean[x][y] == 1) {
          ArrayList<Point> island = new ArrayList<Point>();
          island.add(new Point(x, y));
          islands.add(island);
        }
      }
    }
    if (islands.size() == 0) {
      return 0;
    }
    //If islands have adjusent points, merge them together.
    int currentIsland = 0;
    ArrayList<Integer> mergedIslands = new ArrayList<Integer>();
    while (currentIsland >= 0) {
      currentIsland = -1;
      for (int i = 0, islandsNum = islands.size(); i < islandsNum; i++) {
        for (int j = 0, pointsNum = islands.get(i).size(); j < pointsNum; j++) {
          Point p = islands.get(i).get(j);
          mergedIslands = getAdjustmentIsland(p, i, islands);
          if (mergedIslands.size() > 0) {
            currentIsland = i;
            break;
          }
        }
        if (currentIsland >= 0) {
          break;
        }
      }
      for (int num : mergedIslands) {
        for (int j = 0, pointsNum = islands.get(num).size(); j < pointsNum; j++) {
          Point p = islands.get(num).get(j);
          islands.get(currentIsland).add(p);
        }
      }
      ArrayList<ArrayList<Point>> newIslands = new ArrayList<ArrayList<Point>>();
      for (int i = 0, islandsNum = islands.size(); i < islandsNum; i++) {
        if (mergedIslands.indexOf(i) < 0) {
          newIslands.add(islands.get(i));
        }
      }
      islands = newIslands;
    }
    //Get the maximum island size by iterating through the islands.
    int max = 1;
    for (ArrayList<Point> island : islands) {
      if (island.size() > max) {
        max = island.size();
      }
    }
    return max;
  }


  /**
   * Given the island, and the point searches for any adjusent other island.
   * @param p The point in the island to search from.
   * @param exIsland The index of the island, p came from.
   * @param islands The array of all islands currently available.
   * @return Array of indexes of the adjusent islands.
   */
  static ArrayList<Integer> getAdjustmentIsland(Point p, int exIsland, ArrayList<ArrayList<Point>> islands) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (int i = 0, islandsNum = islands.size(); i < islandsNum; i++) {
      if (i == exIsland) {
        continue;
      }
      for (int j = 0, pointsNum = islands.get(i).size(); j < pointsNum; j++) {
        Point curP = islands.get(i).get(j);
        if ((curP.y == p.y && ((curP.x == p.x + 1) || (curP.x == p.x - 1))) ||
          (curP.x == p.x && ((curP.y == p.y + 1) || (curP.y == p.y - 1)))) {
          result.add(i);
          break;
        }
      }
    }
    return result;
  }

}

