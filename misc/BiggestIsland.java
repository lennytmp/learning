/**
 * Perfomrs search of the biggest "island" in a matrix.
 */

package misc;
import java.util.ArrayList;


//Class that represents one point in the initial matrix.
class Point {
	int x;
	int y;

  /**
   * Constructor, assigning parameters to class properties.
   * @param x X coordinate of the point. 
   * @param y Y coordinate of the point. 
   */  
	Point(int x, int y) {
		this.x = x;
		this.y = y;
  }
}


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
      {1, 0, 0 , 1},
      {1, 1, 0 , 1},
      {1, 0, 0 , 1}
    };
    System.out.println(countBiggestIsland(matrix));
  }


  /**
   * Counts the number points in the biggest island.
   * @param ocean The initial matrix to search in. 
   * @return The number of points in the biggest island. 
   */
  static int countBiggestIsland(int[][] ocean) {
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

