/** 
 * Defines the Edge class.
 */

package graph;
import java.util.ArrayList;


// Edge class.
public class Edge {
  // Edge starting and ending points.
  public Node from, to;

  // Cost of passing throug the edge.
  public int cost;

  public Edge(Node from, Node to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
    this.from.addEdge(this);
  }
}

