package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;


// https://www.hackerrank.com/challenges/crab-graphs
public class CrabGraphs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caseNum = 0; caseNum < cases; caseNum++) {
            ExtendedTree t = new ExtendedTree(sc);
            System.out.println(t.getMaxFlow());
        }
    }
}

class ExtendedTree {
    private Map<Integer, Vertex> nodesById = new HashMap<>();
    private static final int SOURCE_ID = -1;
    private static final int DEST_ID = -2;
    private int vertexesNum, maxFeetNum, edgesNum;
    
    private class QueueElem {
        public int curVertexId;
        public LinkedHashSet<Integer> before;
        
        public QueueElem(int id) {
            this(id, new LinkedHashSet<Integer>());
        }
        
        public QueueElem(int id, LinkedHashSet<Integer> before) {
            curVertexId = id;
            this.before = new LinkedHashSet<>(before);
        }
    }
    
    public ExtendedTree(Scanner sc) {
        vertexesNum = sc.nextInt();
        maxFeetNum = sc.nextInt();
        edgesNum = sc.nextInt();
        nodesById.put(SOURCE_ID, new Vertex(SOURCE_ID));
        nodesById.put(DEST_ID, new Vertex(DEST_ID));
        for (int i = 0; i < edgesNum; i++) {
            int fromId = sc.nextInt();
            int toId = sc.nextInt();
            addConnection(fromId, toId);
        }
        adjustHeadsCapacity();
    }
    
    public int getMaxFlow() {
        boolean pathFound = true;
        int maxFlow = 0;
        while (pathFound) {
            LinkedHashSet<Integer> path = getPath(SOURCE_ID, DEST_ID);
            pathFound = path.size() != 0;
            reduceCapacity(path);
            if (pathFound) {
              maxFlow++; // We know that capacity of the path is always 1 here.
            }
        }
        return maxFlow;
    }
    
    private void reduceCapacity(LinkedHashSet<Integer> path) {
        boolean first = true;
        int prev = 0;
        for (int id : path) {
            if (!first) {
                nodesById.get(prev).decCapacity(id);
                if (isEdgeInternal(prev, id)) {
                    nodesById.get(id).incCapacity(prev); // Allow flow backward.
                }
            }
            first = false;
            prev = id;
        }
    }
    
    private LinkedHashSet<Integer> getPath(int fromId, int toId) {
        List<QueueElem> queue = new LinkedList<QueueElem>();
        Set<Integer> visited = new HashSet<Integer>();
        QueueElem t = new QueueElem(fromId);
        queue.add(t);
        visited.add(fromId);
        while (queue.size() > 0) {
            QueueElem cur = queue.remove(0);
            Vertex current = nodesById.get(cur.curVertexId);
            if (toId == cur.curVertexId) {
                cur.before.add(toId);
                return cur.before;
            }
            Set<Integer> children = current.getChildren();
            for (int childId : children) {
                if (!visited.contains(childId) && current.hasCapacity(childId)) {
                    QueueElem q = new QueueElem(childId, cur.before);
                    q.before.add(cur.curVertexId);
                    visited.add(childId);
                    queue.add(q);
                }
            }
        }
        return new LinkedHashSet<>();
    }
    
    private void adjustHeadsCapacity() {
        Vertex source = nodesById.get(SOURCE_ID);
        Set<Integer> heads = source.getChildren();
        for (int id : heads) {
            Vertex n = nodesById.get(id);
            int capacity = Math.min(maxFeetNum, n.getDegree());
            source.setCapacity(id, capacity);
        }
    }
    
    private void addConnection(int fromId, int toId) {
        addHeadFoot(fromId);
        addHeadFoot(toId);
        nodesById.get(getHeadId(fromId))
                 .addChild(getFootId(toId));
        nodesById.get(getHeadId(toId))
                 .addChild(getFootId(fromId));
        if (isEdgeInternal(fromId, toId)) {
            Vertex toFoot = nodesById.get(getFootId(toId));
            toFoot.addChild(getHeadId(fromId));
            toFoot.setCapacity(getHeadId(fromId), 0);
            Vertex fromFoot = nodesById.get(getFootId(fromId));
            fromFoot.addChild(getHeadId(toId));
            fromFoot.setCapacity(getHeadId(toId), 0);
        }
    }
    
    private boolean isEdgeInternal(int fromId, int toId) {
        return fromId != SOURCE_ID && toId != SOURCE_ID &&
            toId != DEST_ID && toId != DEST_ID;
    }
    
    private void addHeadFoot(int index) {
        int headId = getHeadId(index);
        int footId = getFootId(index);
        if (nodesById.get(headId) == null) {  
            nodesById.put(headId, new Vertex(headId));
            nodesById.put(footId, new Vertex(footId));
            nodesById.get(SOURCE_ID).addChild(headId);
            nodesById.get(footId).addChild(DEST_ID);
        }
    }
    
    private int getHeadId(int index) {
        return index * 2;
    }
    
    private int getFootId(int index) {
        return index * 2 + 1;
    }
}

class Vertex {
    private int id;
    private static final int DEFAULT_CAPACITY = 1;
    private Map<Integer, Integer> capacityByChildId = new HashMap<>();
        
    public Vertex(int index) {
        id = index;
    }
    
    public void addChild(int childId) {
        addChild(childId, DEFAULT_CAPACITY);
    }
    
    public void addChild(int childId, int capacity) {
        capacityByChildId.put(childId, capacity);
    }
    
    public Set<Integer> getChildren() {
        return capacityByChildId.keySet();
    }
    
    public boolean hasCapacity(int childId) {
        return capacityByChildId.get(childId) > 0;
    }
    
    public void setCapacity(int childId, int capacity) {
        capacityByChildId.put(childId, capacity);
    }
    
    public void decCapacity(int childId) {
        capacityByChildId.put(childId,
                              capacityByChildId.get(childId) - 1);
    }
    
    public void incCapacity(int childId) {
        capacityByChildId.put(childId,
                              capacityByChildId.get(childId) + 1);
    }
    
    public int getDegree() {
        return capacityByChildId.size();
    }
}
