/**
 * Implements Dijkstran algorithm.
 */

package graph;

import java.util.ArrayList;
import heap.Heap;
import java.util.HashMap;


public class Dijkstra {
  /**
   * Sets the adjList of the graph and calls getDistances function. 
   * @param args Array of arguments for launching the program. Ignored.
   */
  public static void main(String[] args) {
    int[][] adjList = {
      {1, 1, 2}, {1, 1, 4}, {1, 5, 3},
      {2, 1, 5},
      {3, 3, 6}, {3, 8, 1},
      {5, 2, 3}
    };
    Graph graph = new Graph(adjList);
    getDistances(graph, 3);
  }

  /**
   * Finds the distance of each node from the starting node with Dijkstra algorithm.
   * @param graph The graph object.
   * @param from The index of the node to calculate distance from.
   */
  public static void getDistances(Graph graph, int from) {
    Heap edgesQueue = new Heap(false);
    int currentCost = 0;
    HashMap<Integer, Integer> distanceByIndex = new HashMap<Integer, Integer>();
    distanceByIndex.put(from, 0);
    ArrayList<Edge> newEdges = graph.nodesByIndex.get(from).edges;
    for (Edge e : newEdges) {
      edgesQueue.addObj(e.cost, e);
    }
    Edge next = (Edge)edgesQueue.pullTopObj();

    while (next != null) {
      Node nextNode = next.to;
      if (distanceByIndex.get(nextNode.index) == null) {
        distanceByIndex.put(nextNode.index, distanceByIndex.get(next.from.index) + next.cost);
        newEdges = graph.nodesByIndex.get(nextNode.index).edges;
        for (Edge e : newEdges) {
          edgesQueue.addObj(e.cost, e);
        }
        System.out.println(next.to.index + " takes " + distanceByIndex.get(next.to.index));
      }
      next = (Edge)edgesQueue.pullTopObj();
    }
  }
}
