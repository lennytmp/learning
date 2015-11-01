package graph;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import graph.MinCut;


public class MinCutTest extends junit.framework.TestCase {
 
 
  public void testAddMergedEdge() {
    List<List<Integer>> groups = new ArrayList<List<Integer>>();

    Integer[] tmp = {1, 2};
    List<Integer> group = new ArrayList<Integer>();
    group.addAll(Arrays.asList(tmp));
    groups.add(group);

    Integer[] tmp2 = {5};
    List<Integer> group2 = new ArrayList<Integer>();
    group2.addAll(Arrays.asList(tmp2));
    groups.add(group2);

    int[] merged = {1, 3};
    groups = MinCut.addMergedEdge(groups, merged);
    String label = "Adding to the existing group";
    assertEquals(label, 2, groups.size());
    assertEquals(label, 3, groups.get(0).size());

    label = "Mergin two groups";
    merged[1] = 5;
    groups = MinCut.addMergedEdge(groups, merged);
    assertEquals(label, 1, groups.size());
    assertEquals(label, 4, groups.get(0).size());
  }
  
  public void testNodesNum() {
    int[][] adjList = {
      {1, 2}, {1, 5},
      {2, 5}, {2, 6},
      {5, 6}, 
      {6, 7},
    };
    assertEquals(5, MinCut.getNodes(adjList).length);
  }
}
