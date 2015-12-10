/**
 * Performs breadth first search algortihm to find the shortest path.
 */

package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


// Graph class
class Graph {
	int[][] adjList;

  //Connections to other nodes keyed by node.
  HashMap<Integer, ArrayList<Integer>> nodesByNode = new HashMap<Integer, ArrayList<Integer>>();

  /**
   * Constructor, assigning parameters to class properties and keeping
   * a HashMap of all nodes.
   * @param adjList Adjustment list after contraction.
   */
  Graph(int[][] adjList) {
    this.adjList = adjList;
    for (int[] edge : adjList) {
      ArrayList<Integer> nodes = nodesByNode.get(edge[0]);
      if (nodes == null) {
        nodes = new ArrayList<Integer>();
      }
      if (nodes.indexOf(edge[0]) < 0) {
        nodes.add(edge[1]);
        nodesByNode.put(edge[0], nodes);
      }
    }
  }
}


//Class for storing elements of the queue.
class QueueElement {

  //The value of the node.
	int value;

  //The path from the start to this node.
	ArrayList<Integer> comingFrom;

  /**
   * Constructor, assigning parameters to class properties.
   * @param value Value of the node.
   * @param comingFrom The path from the start to this node.
   */
	QueueElement(int value, ArrayList<Integer> comingFrom) {
		this.value =  value;
		this.comingFrom = comingFrom;
  }


  /**
   * Method to print the QueueElement object.
   * @return String The string to print.
   */
  @Override
  public String toString() {
    return "value: " + value + " from: " + comingFrom;
  }


  /**
   * Static method to print an array of queue elements.
   * @param list The list to print.
   */
  public static void printQueue(ArrayList<QueueElement> list) {
    String result = "";
    for (QueueElement elem : list) {
      result += ' ' + elem.toString();
    }
    System.out.println(result);
  }


  /**
   * @param queue The queue to search in.
   * @param value The value to check in the queue.
   * @return Is the element in queue
   */
  static boolean inQueue(ArrayList<QueueElement> queue, int value) {
    for (QueueElement element : queue) {
      if (element.value == value) {
        return true;
      }
    }
    return false;
  }
}


//Class to find the shortest path.
public class ShortestPath {


  /**
   * Sets an adjustment list, runs the alorithm to get the shortest path
   * and prints the result.
   * @param args Array of arguments for launching the program. Ignored.
   */
  public static void main(String[] args) {
    int[][] adjList = {{1, 2}, {1, 5}, {5, 6}, {2, 6}, {2, 3}, {3, 6}, {6, 7}};
    Graph graph = new Graph(adjList);
    ArrayList<Integer> path = getShortestPath(graph, 1, 7);
    printArrayList(path);
  }


  /**
   * Sets the initial variables for the recursive search to start.
   * @param graph The graph object.
   * @param from The start point.
   * @param to The end point.
   */
  static ArrayList<Integer> getShortestPath(Graph graph, int from, int to) {
    ArrayList<Integer> explored = new ArrayList<Integer>();
    ArrayList<QueueElement> queue = new ArrayList<QueueElement>();
    queue.add(new QueueElement(from, new ArrayList<Integer>()));
    return findPathFromQueue(graph, to, explored, queue);
  }


  /**
   * Recursively called function to process the queue.
   * @param graph The graph object.
   * @param to The end point.
   * @param explored The points that have already been explored.
   * @param queue The queue with elements to check next.
   * @return The path from the start point to the end an point as an
   * array of nodes.
   */
  static ArrayList<Integer> findPathFromQueue(Graph graph, int to, ArrayList<Integer> explored, ArrayList<QueueElement> queue) {
    if (queue.size() == 0) {
      return new ArrayList<Integer>();
    }
    QueueElement fromObj = queue.remove(0);
    int from = fromObj.value;
    explored.add(from);
    ArrayList<Integer> nodes = graph.nodesByNode.get(from);
    if (nodes == null) {
      return findPathFromQueue(graph, to, explored, queue);
    }
    for (int node : nodes) {
      if (node == to) {
        ArrayList<Integer> path = new ArrayList<Integer>(fromObj.comingFrom);
        path.add(fromObj.value);
        path.add(to);
        return path;
      }
      if (explored.indexOf(node) < 0 && !QueueElement.inQueue(queue, node)) {
        ArrayList<Integer> comingFrom = new ArrayList<Integer>(fromObj.comingFrom);
        comingFrom.add(fromObj.value);
        QueueElement newNode = new QueueElement(node, comingFrom);
        queue.add(newNode);
      }
    }
    return findPathFromQueue(graph, to, explored, queue);
  }


  /**
   * Prints an array list of integers.
   * @param list The list to search in.
   */
  static void printArrayList(ArrayList<Integer> list) {
    String result = "";
    for (Integer elem : list) {
      result += ' ' + elem.toString();
    }
    System.out.println(result);
  }


  /**
   * Prints the hashmap object (integer as keys, array lists of integers
   * as values.
   * @param map The hashmap to print.
   */
  static void printHashMap(HashMap<Integer, ArrayList<Integer>> map) {
    for (Integer name : map.keySet()) {
      String key = name.toString();
      String value = map.get(name).toString();
      System.out.println(key + " " + value);
    }
  }
}
