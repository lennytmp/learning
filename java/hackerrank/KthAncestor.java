package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/kth-ancestor
class Node {
    public int index;
    public int parent;
    public ArrayList<Integer> parents;
    
    public Node(int index, int parent) {
        this.index = index;
        this.parent = parent;
    }
    
    public String toString() {
        String result = Integer.toString(index);
        if (parent != 0) {
            result += " <- " + Integer.toString(parent);
        }
        return result;
    }
}

class Tree {
    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
    private HashMap<Integer, ArrayList<Integer>> nodesByParents =
        new HashMap<Integer, ArrayList<Integer>>();
    private boolean insertMode = true;
    private int rootIndex;
    private static final int PARENTS_LIMIT = 100;
    
    
    public void add(int index, int parent) {
        Node n = new Node(index, parent);
        nodes.put(index, n);
        if (parent == 0) {
            this.rootIndex = index;
        }
        ArrayList<Integer> siblings = nodesByParents.get(parent);
        if (siblings == null) {
            siblings = new ArrayList<Integer>();
        }
        siblings.add(index);
        nodesByParents.put(parent, siblings);
        if (!insertMode) {
            propogateParentsToChildren(index);
        }
    }
    
    
    public void remove(int index) {
        Node n = this.get(index);
        if (n == null) {
            return;
        }
        ArrayList<Integer> parentChildren = nodesByParents.get(n.parent);
        if (parentChildren != null) {
            parentChildren.remove(new Integer(index));
            nodesByParents.put(n.parent, parentChildren);
        }
        nodes.remove(index);
        if (!insertMode) {
            propogateParentsToChildren(index);
        }
        nodesByParents.remove(index);
    }
    
    public int getAncestorK(int index, int k) {
        if (k == 0) {
            return index;
        }
        Node n = this.get(index);
        if (n == null) {
            return 0;
        }
        if (k < PARENTS_LIMIT) {
            if (n.parents.size() < k) {
                return 0;
            }
            return n.parents.get(k - 1);
            
        }
        if (n.parents.size() < PARENTS_LIMIT) {
            return 0;
        }
        return getAncestorK(n.parents.get(PARENTS_LIMIT - 1), k - PARENTS_LIMIT);
    }
    
    public void finishInsertMode() {
        this.insertMode = false;
        propogateParentsToChildren(this.rootIndex);
    }
    
    
    public Node get(int index) {
        return nodes.get(index);
    }
    
    @Override
    public String toString() {
        return this.nodes.toString();
    }
    
    private void propogateParentsFromParent(int index) {
        Node n = this.get(index);
        if (n == null) {
            return;
        }
        Node p = this.get(n.parent);
        ArrayList<Integer> newParents = new ArrayList<Integer>();
        if (p != null) {
            newParents.add(n.parent);
            newParents.addAll(p.parents);
        }
        while (newParents.size() > PARENTS_LIMIT) {
            newParents.remove(newParents.size() - 1);
        }
        n.parents = newParents;
    }
    
    private void propogateParentsToChildren(int start) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        propogateParentsFromParent(start);
        ArrayList<Integer> newChildren = nodesByParents.get(start);
        if (newChildren != null) {
            queue.addAll(newChildren);
        }
        while (queue.size() > 0) {
            int index = queue.pop();
            propogateParentsFromParent(index);
            newChildren = nodesByParents.get(index);
            if (newChildren != null) {
                queue.addAll(newChildren);
            }
        }
    }
}

public class KthAncestor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testsNum = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testsNum; i++) {
            Tree t = new Tree();
            int nodesNum = sc.nextInt();
            for (int j = 0; j < nodesNum; j++) {
                handleInitAdd(t, sc);
            }
            t.finishInsertMode();
            int queries = sc.nextInt();
            for (int j = 0; j < queries; j++) {
                int type = sc.nextInt();
                switch (type) {
                    case 0:
                        handleAddResponse(t, sc);
                        break;
                    case 1:
                        handleRemoveResponse(t, sc);
                        break;
                    case 2:
                        System.out.println(handleFindResponse(t, sc));
                        break;
                }
            }
        }
    }
    
    
    private static void handleInitAdd(Tree t, Scanner sc) {
        int index = sc.nextInt();
        int parent = sc.nextInt();
        t.add(index, parent);
    }
    
    
    private static void handleAddResponse(Tree t, Scanner sc) {
        int parent = sc.nextInt();
        int index = sc.nextInt();
        t.add(index, parent);
    }
    
    
    private static void handleRemoveResponse(Tree t, Scanner sc) {
        int index = sc.nextInt();
        t.remove(index);
    }

    
    private static int handleFindResponse(Tree t, Scanner sc) {
        int index = sc.nextInt();
        int k = sc.nextInt();
        return t.getAncestorK(index, k);
    }
}
