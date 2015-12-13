package graph;

import java.util.ArrayList;
import heap.Heap;

public class Dijkstra {


  /**
   * @param args Array of arguments for launching the program. Ignored.
   */
  public static void main(String[] args) {
    int[][] adjList = {
      {1, 1, 2}, {1, 1, 4}, {1, 5, 3},
      {2, 1, 5},
      {3, 2, 5}, {3, 3, 6}
    };
    Graph graph = new Graph(adjList);
    getDistances(graph, 1);
  }


  public static void getDistances(Graph graph, int from) {
    Heap edgesQueue = new Heap(false);
    ArrayList<Edge> fromEdges = graph.nodesByIndex.get(from).edges;
    for (Edge e : fromEdges) {
      System.out.println(e.to.index + " " + e.cost);
    }
  }
}
