 /**
 * Defines class Node. 
 */

package graph;
import java.util.ArrayList;


// Node class.
public class Node {
  // Index of the node.
  int index;
  
  // Edges going from the node.
  public ArrayList<Edge> edges = new ArrayList<Edge>();


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
