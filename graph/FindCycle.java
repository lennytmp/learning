/**
 * Performs depth first search algortihm to detect if there is a cycle.
 */

package graph;

import java.util.ArrayList;
import java.util.HashMap;


// Class to find cycles
public class FindCycle {


  /**
   * Sets an adjustment list, runs the alorithm to get check for the cycle
   * and prints the result.
   * @param args Array of arguments for launching the program. Ignored.
   * @export
   */
  public static void main(String args[]) {
    int[][] adjList = {{1, 2}, {1, 3}, {3, 2}, {3, 5}, {5, 1}};
    Graph graph = new Graph(adjList);
    boolean result = hasCycle(graph, 1);
    System.out.println(Boolean.toString(result));
  }


  /**
   * Sets initial varialbes and makes the call for recursive function
   * to detect if there is a cycle.
   * @param graph The graph to search in.
   * @param from The pint where to detect a cycle in.
   * @export
   */
  static boolean hasCycle(Graph graph, int from) {
    ArrayList<Integer> queue = new ArrayList<Integer>();
    ArrayList<Integer> explored = new ArrayList<Integer>();
    queue.add(from);
    return dfSearch(graph, from, queue, explored);
  }


  /**
   * Recursive function: gets element from the queue and checks adjusted
   * nodes in search of from point. Otherwise adds the nodes to the the queue.
   * @param Graph The graph to search in.
   * @param from The point to search cycles for.
   * @param queue The queue of elements to research.
   * @param explored Already explored nodes.
   */
  static boolean dfSearch(Graph graph, int from, ArrayList<Integer> queue, ArrayList<Integer> explored) {
    if (queue.size() == 0) {
      return false;
    }
    int curNode = queue.remove(queue.size() - 1);
    explored.add(curNode);
    ArrayList<Integer> nodes = graph.nodesByNode.get(curNode);
    if (nodes != null) {
      for (int node : nodes) {
        if (node == from) {
          return true;
        }
        if (explored.indexOf(node) < 0 && queue.indexOf(node) < 0) {
            queue.add(node);
        }
      }
    }
    return dfSearch(graph, from, queue, explored);
  }
}
