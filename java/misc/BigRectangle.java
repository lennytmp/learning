import java.util.Arrays;

public class BigRectangle {
  public static void main(String[] args) {
    int[][] rect1 = {{0,0}, {3,0}, {3,2}, {0,2}};
    int[][] rect2 = {{0,2}, {1,2}, {1,3}, {0,3}};
    int[][] rect3 = {{1,2}, {3,2}, {3,3}, {1,3}};
    System.out.println(doFormBigRect(new int[][][] {rect1, rect2, rect3}));
  }

  private static boolean doFormBigRect(int[][][] rectangles) {
    // TODO: add checks that rectangles are set in the correct format
    int[][] edgePoints = getEdgePoints(rectangles);
    if (edgePoints[0][1] != edgePoints[1][1]
        || edgePoints[1][0] != edgePoints[2][0]
        || edgePoints[2][1] != edgePoints[3][1]
        || edgePoints[3][0] != edgePoints[0][0]) {
      System.out.println("Not clear edge lines");
      return false;
    }
    int total = 0;
    for (int[][] rect : rectangles) {
      total += getSpace(rect);
    }
    if (getSpace(edgePoints) != total) {
      System.out.println("Space doesn't add up");
      return false;
    }
    if (anyIntersects(rectangles)) {
      System.out.println("Intersection found");
      return false;
    }
    return true;
  }

  private static boolean anyIntersects(int[][][] rectangles) {
    for (int i = 0; i < rectangles.length; i++) {
      int[][] cur = rectangles[i];
      for (int j = 0; j < rectangles.length; j++) {
        if (i == j) {
          continue;
        }
        int[][] check = rectangles[j];
        for (int[] point : cur) {
          if (point[0] > check[0][0] && point[0] < check[1][0]
              && point[1] > check[1][1] && point[1] < check[2][1]) {
            System.out.println(i + " intersects " + j);
            System.out.println(Arrays.toString(point));
            return true; 
          }
        }
      }
    }
    return false;
  }

  private static int getSpace(int[][] rectangle) {
    return (rectangle[1][0] - rectangle[0][0]) *
      (rectangle[2][1] - rectangle[1][1]);
  }

  private static int[][] getEdgePoints(int[][][] rectangles) {
    int[][] edgePoints = new int[4][2];
    boolean isSet = false;
    for (int[][] rect : rectangles) {
      for (int[] point : rect) {
        if (!isSet) {
          for (int[] edgePoint : edgePoints) {
            edgePoint = point;
          }
          isSet = true;
          continue;
        }
        if (edgePoints[0][0] >= point[0] && edgePoints[0][1] >= point[1]) {
          edgePoints[0] = point;
        }
        if (edgePoints[1][0] <= point[0] && edgePoints[1][1] >= point[1]) {
          edgePoints[1] = point;
        }
        if (edgePoints[2][0] <= point[0] && edgePoints[2][1] <= point[1]) {
          edgePoints[2] = point;
        }
        if (edgePoints[3][0] >= point[0] && edgePoints[3][1] <= point[1]) {
          edgePoints[3] = point;
        }
      }
    }
    return edgePoints;
  }
}
