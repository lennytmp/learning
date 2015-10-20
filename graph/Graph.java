import java.util.Random;
import java.util.HashSet;


final class ContractionResult {
  public final int[][] adjList;
  public final int[] nodesMerged;

  public ContractionResult(int[][] adjList, int[] nodesMerged) {
    this.adjList = adjList;
    this.nodesMerged = nodesMerged;
  }
}

public class Graph {

  // Generator of random numbers.
  static Random generator = new Random();

  public static void main(String[] args) {
    int[][] adjList = {
      {1, 2}, {1, 5},
      {2, 5}, {2, 6},
      {5, 6}, 
      {6, 7},
    };
    printGraph(adjList);
    System.out.println("Nodes: " + Integer.toString(nodesNum(adjList)));
    int size =  nodesNum(adjList);
    while (size > 2) {
      ContractionResult iteration = contractRandomEdge(adjList);
      adjList = iteration.adjList;
      size = nodesNum(adjList);
      printGraph(adjList);
      System.out.println("===");
    }
    System.out.println("Mincut: " + Integer.toString(adjList.length));
  }


  public static ContractionResult contractRandomEdge(int[][] adjList) {
    int edgeIndex = generator.nextInt(adjList.length);
    int[] delEdge = {adjList[edgeIndex][0], adjList[edgeIndex][1]};
    int num = 0;
    System.out.println("Contracting " +
                       Integer.toString(delEdge[0]) + " " +
                       Integer.toString(delEdge[1]));
    //Counting number of paralel edges like the chosen one
    for (int[] edge : adjList) {
      if (isParallelEdge(edge, delEdge)) {
        num++;
      }
    }
    int[][] result = new int[adjList.length - num][2];
    //Creating new graph
    int k = 0;
    for (int i = 0; i < adjList.length; i++) {
      if (isParallelEdge(adjList[i], delEdge)) {
        continue;
      }
      for (int j = 0; j < 2; j++) {
        if (adjList[i][j] == delEdge[1]) {
          result[k][j] = delEdge[0];
        } else {
          result[k][j] = adjList[i][j];
        }
      }
      k++;
    }
    printGraph(result);
    System.out.println("Nodes: " + Integer.toString(nodesNum(result)));
    return new ContractionResult(result, delEdge);
  }
  

  static boolean isParallelEdge(int[] edge1, int[] edge2) {
    return ((edge1[0] == edge2[0] && edge1[1] == edge2[1]) ||
            (edge1[0] == edge2[1] && edge1[1] == edge2[0]));
  }


  static int nodesNum(int[][] adjList) {
    HashSet<Integer> nodes = new HashSet<Integer>();
    for (int[] edge : adjList) {
      for (int node : edge) {
        nodes.add(node);
      }
    }
    return nodes.size();
  }


  public static void printGraph(int[][] adjList) {
    for (int[] edge : adjList) {
      System.out.println(Integer.toString(edge[0]) + " -> " +
        Integer.toString(edge[1]));
    }
  }
}
