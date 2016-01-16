package euler;

import java.util.PriorityQueue;
import java.util.ArrayList;

class Problem18 {
  static int[][] tree = {
    {75},
    {95, 64},
    {17, 47, 82},
    {18, 35, 87, 10},
    {20, 4, 82, 47, 65},
    {19, 1, 23, 75, 3, 34},
    {88, 2, 77, 73, 7, 63, 67},
    {99, 65, 4, 28, 6, 16, 70, 92},
    {41, 41, 26, 56, 83, 40, 80, 70, 33},
    {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
    {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
    {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
    {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
    {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
    {04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}
  };


  private static class Point {
    int x, y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }


  private static class QueueElem implements Comparable<QueueElem> {
    int sum;
    String path = "";
    Point p;
    
    public QueueElem(Point p, int sum, String path) {
      this.p = p;
      this.sum = sum;
      this.path = path;
    }


    public int 	compareTo(QueueElem elem) {
      return elem.sum - this.sum;
    }
  }


  public static void main(String[] args) {
    PriorityQueue<QueueElem> queue = new PriorityQueue<QueueElem>();
    QueueElem current = new QueueElem(new Point(0, 0), tree[0][0], Integer.toString(tree[0][0]));
    int max = 0;
    while (current != null) {
      if (current.p.y == tree.length - 1) {
        if (current.sum > max) {
          max = current.sum;
        }
      } else {
        int[][] childrenDif = {{0,1}, {1,1}};
        for (int[] childDif : childrenDif) {
          int elem =  tree[current.p.y + childDif[1]][current.p.x + childDif[0]];
          queue.add(new QueueElem(
            new Point(current.p.x + childDif[0], current.p.y + childDif[1]),
            elem + current.sum,
            current.path + "+" + elem
          ));
        }
      }
      current = queue.poll();
    }
    System.out.println("Biggest is: " + max);
  }
}
