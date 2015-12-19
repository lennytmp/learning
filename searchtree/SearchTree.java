/**
 * Binary Search tree implementation.
 */

package searchtree;
import java.util.ArrayList;

public class SearchTree {
  //Root element.
  Node root;


  /**
   * Sets the tree.
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
          curElem.leftChild = n;
          n.parent = curElem;
          break;
        }
      } else {
        if (curElem.rightChild != null) {
          curElem = curElem.rightChild;
        } else {
          curElem.rightChild = n;
          n.parent = curElem;
          break;
        }
      }
    }
  }

  
  /**
   * Returns the tree as a string starting with the root value.
   * @return String to print.
   */
  @Override
  public String toString() {
    String result = "";
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
