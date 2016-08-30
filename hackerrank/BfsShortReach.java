import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;


// https://www.hackerrank.com/challenges/bfsshortreach
public class BfsShortReach {
    private static final int LENGTH = 6;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for (int caseNum = 0; caseNum < numCases; caseNum++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < m; i++) {
                int from = sc.nextInt() - 1;
                int to = sc.nextInt() - 1;
                if (nodes[from] == null) {
                    nodes[from] = new Node();
                }
                if (nodes[to] == null) {
                    nodes[to] = new Node();
                }
                nodes[from].children.add(to);
                nodes[to].children.add(from);
            }
            int s = sc.nextInt() - 1;
            int[] distances = getDistances(nodes, s);
            int last = distances.length - 1;
            if (s == last) {
                last--;
            }
            for (int i = 0; i < distances.length; i++) {
                if (i == s) {
                    continue;
                }
                if (distances[i] == 0) {
                    distances[i] = -1;
                }
                System.out.print(distances[i]);
                if (i < last) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    private static int[] getDistances(Node[] nodes, int s) {
        int[] result = new int[nodes.length];
        Set<Integer> visited = new HashSet<>();
        List<QueueElem> queue = new LinkedList<QueueElem>();
        queue.add(new QueueElem(s, 0));
        while (queue.size() > 0) {
            QueueElem cur = queue.remove(0);
            if (!visited.contains(cur.id)) {
                visited.add(cur.id);
                result[cur.id] = cur.pathLength;
            }
            if (nodes[cur.id] == null) {
                continue;
            }
            List<Integer> children = nodes[cur.id].children;
            for (int i : children) {
                if (!visited.contains(i)) {
                    queue.add(new QueueElem(i, cur.pathLength + LENGTH));
                }
            }
        }
        return result;
    }
}

class Node {
    List<Integer> children = new LinkedList<>();
    
    @Override
    public String toString() {
        return children.toString();
    }
}

class QueueElem {
    int id;
    int pathLength;
    QueueElem(int id, int pathLength) {
        this.id = id;
        this.pathLength = pathLength;
    }
}
