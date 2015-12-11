/** 
 * Defines the Graph class.
 */

package graph;
import java.util.ArrayList;
import java.util.HashMap;


// Graph class.
public class Graph {
	int[][] adjList;

  // Hash table to store nodes by index. 
  HashMap<Integer, Node> nodesByIndex = new HashMap<Integer, Node>();


  /**
   * Constructor, assigning parameters to class properties and keeping
   * a HashMap of all nodes.
   * @param adjList Adjustment list: two params for no edge weights,
   * three params for edges with weight.
   */
  public Graph(int[][] adjList) {
    this.adjList = adjList;
    for (int[] edge : adjList) {
      Node from = nodesByIndex.get(edge[0]);
      if (from == null) {
        from = addNodeWithIndex(edge[0]);
      }
      int toIndex = edge[1], cost = 0;
      if (edge.length == 3) {
        toIndex = edge[2];
        cost = edge[1];
      }
      Node to = nodesByIndex.get(toIndex);
      if (to == null) {
        to = addNodeWithIndex(toIndex);
      }
      Edge e = new Edge(from, to, cost);
      from.addEdge(e);
    }
  }


  public Node addNodeWithIndex(int index) {
    Node n = new Node(index);
    nodesByIndex.put(index, n);
    return n;
  }


  public ArrayList<Integer> getNeighborIndexes(int index) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    Node a = nodesByIndex.get(index);
    if (a == null) {
      return result;
    }
    ArrayList<Node> arr = a.getNeighbors();
    for (Node n : arr) {
      result.add(n.index);
    }
    return result;
  }
}


// Edge class.
class Edge {
  // Edge starting and ending points.
  Node from, to;

  // Cost of passing throug the edge.
  int cost;

  public Edge(Node from, Node to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
    this.from.addEdge(this);
  }
}


// Node class.
class Node {
  // Index of the node.
  int index;
  
  // Edges going from the node.
  ArrayList<Edge> edges = new ArrayList<Edge>();


  public Node(int index) {
    this.index = index;
  }


  public void addEdge(Edge e) {
    this.edges.add(e);
  }

  
  public ArrayList<Node> getNeighbors() {
    ArrayList<Node> result = new ArrayList<Node>();
    for (Edge e : edges) {
      result.add(e.to);
    }
    return result;
  }
}
