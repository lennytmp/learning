package heap;
import java.util.ArrayList;
import java.util.Arrays;



public class Heap {
  // Root element.
  Node root;

  // If it's maxHeap. False means minHeap.
  boolean maxHeap;

  /**
   * Heap constructor.
   * @param maxHeap If it's maxHeap. False means minHeap.
   */
  public Heap(boolean maxHeap) {
    this.maxHeap = maxHeap;
  }

  /**
   * Adds an element to the heap.
   * @param element Node to add to the heap.
   */
  public void add(Node element) {
    if (root == null) {
      root = element;
      root.level = 0;
    } else {
      Node el = getFirstFreeParent();
      el.addChild(element);
      heapify(element);
    }
  }


  /**
   * Gets the node in a tree by it's index.
   * @param index Index of the node to search.
   * @return The node with the index or null.
   */
  public Node findByIndex(int index) {
    if (root == null) {
      return null;
    }
    ArrayList<Node> queue = new ArrayList();
    queue.add(root);
    while (queue.size() > 0) {
      Node node = queue.remove(0);
      if (node.index == index) {
        return node;
      }
      queue.addAll(node.getChildren());
    }
    return null;
  }


  /**
   * Gets the top node (root) and removes it from the heap.
   * @return The node with max value or null.
   */
  public Node pullTop() {
    return remove(root.index);
  }


  /**
   * Removes elements by index and returns it.
   * @param index Index of the node to remove.
   * @return Removed node.
   */
  public Node remove(int index) {
    Node elem = findByIndex(index);
    if (elem == null) return null;
    Node last = getLastElement();
    if (elem == last) {
      elem.parent.removeChild(elem);
      return elem;
    }
    Node lastParent = last.parent;
    if (elem.parent != null) {
      Node p = elem.parent;
      p.removeChild(elem);
      lastParent.removeChild(last);
      p.addChild(last);
    } else {
      lastParent.removeChild(last);
      last.level = root.level;
      root = last;
    }
    for (int i = 0; i < elem.children.length; i++) {
      if (elem.children[i] != null) {
        elem.children[i].parent = last;
      }
    }
    Node[] children = elem.children;
    last.children = children;

    ArrayList<Node> queue = new ArrayList();
    queue.add(last);
    while (queue.size() > 0) {
      Node node = queue.remove(0);
      Node topChild = node.getChild(maxHeap);
      if (topChild != null) {
        if ((maxHeap && topChild.value > node.value) ||
              (!maxHeap && topChild.value < node.value)) {
          topChild.swap(topChild.parent);
          queue.add(topChild);
        }
      }
    }
    return elem;
  }


  /**
   * Enforces the heap property starting from the element.
   * @param elem The node element to start with.
   */
  public void heapify(Node elem) {
    if (elem.parent != null) {
      if ((maxHeap && elem.parent.value < elem.value) ||
            (!maxHeap && elem.parent.value > elem.value)) {
        elem.swap(elem.parent);
        heapify(elem.parent);
      }
    }
  }


  /**
   * Prints the heap starting with the root value.
   * @return String to print.
   */
  @Override
  public String toString() {
    String result = "";
    int currentLevel = 0;
    ArrayList<Node> queue = new ArrayList();
    queue.add(root);
    while (queue.size() > 0) {
      Node node = queue.remove(0);
      if (currentLevel < node.level) {
        result += "\n";
      }
      currentLevel = node.level;
      result += node.toString() + "   ";
      queue.addAll(node.getChildren());
    }
    return result;
  }


  /**
   * Get the last element of the heap, last row, last column.
   * @return Node in the end or null.
   */
  protected Node getLastElement() {
    ArrayList<Node> queue = new ArrayList();
    if (root == null) return null;
    queue.add(root);
    Node element = null;
    while (queue.size() > 0) {
      element = queue.remove(0);
      queue.addAll(element.getChildren());
    }
    return element;
  }


  /**
   * Get the first element, which has less than 2 children.
   * @return Node in the end or null.
   */
  protected Node getFirstFreeParent() {
    ArrayList<Node> queue = new ArrayList();
    if (root == null) return null;
    queue.add(root);
    while (queue.size() > 0) {
      Node element = queue.remove(0);
      if (element.getChildrenNum() < 2) {
        return element;
      }
      queue.addAll(element.getChildren());
    }
    throw new RuntimeException("No childless element found");
  }


  /**
   * Sets the tree, removes an item and prints the results.
   * @param args Array of arguments for launching the program. Ignored.
   */
  public static void main(String[] args) {
    Heap tree = new Heap(true);
    class NodeObj {
      public String hello;
      public NodeObj(int i) {
        hello = "World " + i;
      }
    }
    for (int i = 0; i < 10; i++) {
      tree.add(new Node(i, new NodeObj(i)));
    }
    System.out.println(tree);
    tree.remove(3);
    System.out.println(tree);

    NodeObj n = (NodeObj)tree.pullTop().obj;
    System.out.println(n.hello);
    System.out.println(tree);
    n = (NodeObj)tree.pullTop().obj;
    System.out.println(n.hello);
    System.out.println(tree);
  }
}
