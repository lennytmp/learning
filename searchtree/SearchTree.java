/**
 * Binary Search tree implementation.
 */

package searchtree;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchTree {
  //Root element.
  Node root;


  /**
   * Sets the tree, calls functions and prints results.
   * @param args Array of arguments for launching the program. Ignored.
   */
  public static void main(String[] args) {
    SearchTree tree = new SearchTree();
    tree.add(10);
    tree.add(20);
    tree.add(2);
    tree.add(29);
    tree.add(21);
    tree.add(11);
    tree.add(1);
    System.out.println(tree);
    System.out.println("Removing 29");
    tree.remove(29);
    System.out.println(tree);
    System.out.println("Removing 21");
    tree.remove(21);
    System.out.println(tree);
    tree.add(12);
    tree.add(13);
    tree.add(3);
    System.out.println("Adding 3 more elements");
    System.out.println(tree);
    System.out.println("Removing 10");
    tree.remove(10);
    System.out.println(tree);
    System.out.println("Removing 2");
    tree.remove(2);
    System.out.println(tree);
  }


  /**
   * Adds an element to the tree.
   * @param element Node to add to the heap.
   */
  public void add(int value) {
    Node n = new Node(value);
    if (root == null) {
      root = n;
      return;
    }
    Node curElem = root;
    while (true) {
      if (curElem.value > value) {
        if (curElem.leftChild != null) {
          curElem = curElem.leftChild;
        } else {
          curElem.addChild(n);
          break;
        }
      } else {
        if (curElem.rightChild != null) {
          curElem = curElem.rightChild;
        } else {
          curElem.addChild(n);
          break;
        }
      }
    }
  }

  /**
   * Removes element by value. 
   * @param value Value of the node to remove. 
   */
  public void remove(int value) {
    Node n = findByValue(value);
    if (n == null) {
      return;
    }
    if (n.childNum() == 0) {
      if (n == root) {
        root = null;
      } else {
        n.parent.removeChild(n);
      }
      return;
    }
    if (n.childNum() == 1) {
      if (n == root) {
        root = n.getTheOnlyChild();
      } else {
        n.parent.removeChild(n);
        n.parent.addChild(n.getTheOnlyChild());
      }
      return;
    }
    Node localMax = getMaxFrom(n);
    remove(localMax.value);
    n.value = localMax.value;
    Node curElem = n;
    while (true) {
      if (curElem.rightChild == null ||
          curElem.value < curElem.rightChild.value) {
        return;
      }
      int tmp = curElem.rightChild.value;
      curElem.rightChild.value = curElem.value;
      curElem.value = tmp;
      curElem = curElem.rightChild;
    }
  }


  /**
   * Gets the maximum starting from the node.
   * @param n Node to start with.
   * @return Node with the maximum value.
   */
  Node getMaxFrom(Node n) {
    Node curElem = n;
    while (curElem.rightChild != null) {
      curElem = curElem.rightChild;
    }
    return curElem;
  }


  /**
   * Finds the node in a tree by the value provided.
   * @param value Value to search for. 
   * @return Node or null if not found.
   */
  public Node findByValue(int value) {
    Node curElem = root;
    while (curElem != null) {
      if (curElem.value == value) {
        return curElem;
      }
      if (curElem.value > value) {
        curElem = curElem.leftChild;
      } else {
        curElem = curElem.rightChild;
      }
    }
    return null;
  }

  
  /**
   * Returns the tree as a string starting with the root value.
   * @return String to print.
   */
  @Override
  public String toString() {
    String result = "";
    if (root == null) {
      return "";
    }
    ArrayList<Node> queue = new ArrayList<Node>();
    queue.add(root);
    while (queue.size() > 0) {
      Node curElem = queue.remove(0);
      result += curElem.toString() + "\n";
      if (curElem.leftChild != null) {
        queue.add(curElem.leftChild);
      } 
      if (curElem.rightChild != null) {
        queue.add(curElem.rightChild);
      }
    }
    return result;
  }
}
