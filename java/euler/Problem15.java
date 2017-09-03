package euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigInteger;

class Problem15 {
  private static class Point {
    public int x, y;
    public int[][] grid;


    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      return Integer.toString(this.x) + "_" +
        Integer.toString(this.y);
    }
  }

  
  static HashMap<String, BigInteger> ready = new  HashMap<String, BigInteger>();


  static BigInteger getVariantsNum(Point cur) {
    if (ready.get(cur.toString()) != null) {
      return ready.get(cur.toString());
    }
    if (cur.x == 0 || cur.y == 0) {
      return BigInteger.ONE;
    }
    BigInteger result = getVariantsNum(new Point(cur.x - 1, cur.y)).add(
        getVariantsNum(new Point(cur.x, cur.y - 1)));
    ready.put(cur.toString(), result);
    return result;
  }


  public static void main(String[] args) {
    System.out.println(getVariantsNum(new Point(20, 20)).toString());
  }
}
