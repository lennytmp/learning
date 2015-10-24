package graph;

import java.util.Arrays;
import java.util.ArrayList;
import graph.Graph;
import java.util.List;


public class GraphTest extends junit.framework.TestCase {
 
 
  public void testAddMergedEdge() {
    List<List<Integer>> groups = new ArrayList<List<Integer>>();

    Integer[] tmp = {1, 2};
    List<Integer> group = new ArrayList<Integer>();
    group.addAll(Arrays.asList(tmp));
    groups.add(group);

    int[] merged = {1, 3};
    groups = Graph.addMergedEdge(groups, merged);
    String label = "Adding to the existing group";
    assertEquals(label, 1, groups.size());
    assertEquals(label, 3, groups.get(0).size());

    label = "Adding a new group";
    merged[0] = 6;
    merged[1] = 8;
    groups = Graph.addMergedEdge(groups, merged);
    assertEquals(label, 2, groups.size());
    assertEquals(label, 3, groups.get(0).size());
    assertEquals(label, 2, groups.get(1).size());

    label = "Mergin two groups";
    merged[1] = 1;
    groups = Graph.addMergedEdge(groups, merged);
    assertEquals(label, 1, groups.size());
    assertEquals(label, 5, groups.get(0).size());
  }
  
  public void testNodesNum() {
    int[][] adjList = {
      {1, 2}, {1, 5},
      {2, 5}, {2, 6},
      {5, 6}, 
      {6, 7},
    };
    assertEquals(5, Graph.nodesNum(adjList));
  }
}
