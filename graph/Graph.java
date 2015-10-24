package graph;

import java.util.Random;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


final class ContractionResult {
  public final int[][] adjList;
  public final int[] merged;

  public ContractionResult(int[][] adjList, int[] merged) {
    this.adjList = adjList;
    this.merged = merged;
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
    List<List<Integer>> groups = new ArrayList<List<Integer>>();
    while (size > 2) {
      ContractionResult iteration = contractRandomEdge(adjList);
      adjList = iteration.adjList;
      groups = addMergedEdge(groups, iteration.merged);
      int[] merged = iteration.merged;
      size = nodesNum(adjList);
      printGraph(adjList);
      System.out.println("===");
    }
    System.out.println("Mincut: " + Integer.toString(adjList.length));
    System.out.println(groups.toString());
  }


  /**
   * Manges groups of merged edges. There are three cases:
   * If nodes are set in one group, then add new node to this group.
   * If nodes are not in any group, then create new group with these nodes.
   * If nodes are part of two groups, merge the group
   */
   static List<List<Integer>> addMergedEdge(List<List<Integer>> groups, int[] merged) {
    List<Integer> intersected = new ArrayList<Integer>();
    for (int i = 0; i < groups.size(); i++) {
      List<Integer> group = groups.get(i);
      for (int j = 0; j < group.size(); j++) {
        if (group.get(j) == merged[0] || group.get(j) == merged[1]) {
          intersected.add(i);
        }
      }
    }
    if (intersected.size() == 0) {
      Integer[] tmp = {merged[0], merged[1]};
      groups.add(new ArrayList<Integer>(Arrays.asList(tmp)));
    }
    else if (intersected.size() == 1) {
      List<Integer> edges = groups.get(intersected.get(0));
      for (int i = 0; i < 2; i++) {
        if (edges.indexOf(merged[i]) < 0) {
          edges.add(merged[i]);
        }
      }
    }
    else {
      List<Integer> group = groups.get(intersected.get(0));
      group.addAll(groups.get(intersected.get(1)));
      groups.remove(intersected.get(1).intValue());
    }
    return groups;
  }


  public static ContractionResult contractRandomEdge(int[][] adjList) {
    int edgeIndex = generator.nextInt(adjList.length);
    int[] delEdge = {adjList[edgeIndex][0], adjList[edgeIndex][1]};
    int[][] resultType = {}; 
    int num = 0;
    System.out.println("Contracting " +
                       Integer.toString(delEdge[0]) + " " +
                       Integer.toString(delEdge[1]));
    List<int[]> result = new ArrayList<int[]>();
    //Creating new graph
    for (int i = 0; i < adjList.length; i++) {
      if (isParallelEdge(adjList[i], delEdge)) {
        continue;
      }
      int[] tmpEdge = new int[2];
      for (int j = 0; j < 2; j++) {
        if (adjList[i][j] == delEdge[1]) {
          tmpEdge[j] = delEdge[0];
        } else {
          tmpEdge[j] = adjList[i][j];
        }
      }
      result.add(tmpEdge);
    }
    System.out.println("Nodes: " + Integer.toString(nodesNum(result.toArray(resultType))));
    return new ContractionResult(result.toArray(resultType), delEdge);
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
