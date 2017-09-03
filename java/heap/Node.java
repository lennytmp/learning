package heap;
import java.util.ArrayList;
import java.util.Arrays;

class Node {
  // Value of the node.
  public int value;

  // Index of the node.
  public int index;

  // Object of the node.
  public Object obj;

  // Counter to set the index
  public static int indexCnt = 0;

  // The level in the heap, set externally.
  public int level;

  // Two children, null by default.
  public Node[] children = new Node[2];

  // Parent node.
  public Node parent;


  /**
   * Constructor for the node.
   * @param value The value of the node.
   * @param index The index of the node.
   */
  public Node(int value, Object obj) {
    this.value = value;
    this.obj = obj;
    this.index = Node.indexCnt;
    Node.indexCnt++;
  }


  /**
   * Get the number of children.
   * @return Number of children.
   */
  public int getChildrenNum() {
    int i = 0;
    for (Node el : children) {
      if (el != null) i++;
    }
    return i;
  }


  /**
   * Adds child to the node or ignores it if it's full;
   * also updates the child's parent value.
   * @param child Child node to add.
   */
  public void addChild(Node child) {
    for (int i = 0; i < children.length; i++) {
      if (children[i] == null) {
        children[i] = child;
        child.parent = this;
        child.level = this.level + 1;
        return;
      }
    }
    throw new RuntimeException("Trying to add child to full node");
  }


  /**
   * Removes the child and updates child parent value.
   * @param child Child node to remove.
   */
  public void removeChild(Node child) {
    for (int i = 0; i < children.length; i++) {
      if (children[i] == child) {
        child.parent = null;
        children[i] = null;
      }
    }
  }


  /**
   * Get children as an ArrayList without nulls.
   * @return ArrayList of children without nulls.
   */
  public ArrayList<Node> getChildren() {
    ArrayList<Node> result = new ArrayList();
    for (Node ch : children) {
      if (ch != null) {
        result.add(ch);
      }
    }
    return result;
  }


  /**
   * Get the child with max value.
   * @return Node with max value or null.
   */
  public Node getChild(boolean max) {
    Node result = children[0];
    for (Node ch : children) {
      if (ch != null && ch.value > result.value) {
        if ((max && ch.value > result.value) ||
              (!max && ch.value < result.value)) {
          result = ch;
        }
      }
    }
    return result;
  }


  /**
   * Swaps the node with partner (index, obj, value).
   * @param partner The node to swap with.
   */
  public void swap(Node partner) {
    int tmp = partner.value;
    partner.value = this.value;
    this.value = tmp;
     
    tmp = partner.index;
    partner.index = this.index;
    this.index = tmp;

    Object tmpObj = partner.obj;
    partner.obj = this.obj;
    this.obj = tmpObj;
  }


  /**
   * Prints the node value.
   * @return String to print.
   */
  @Override
  public String toString() {
    return Integer.toString(this.value);
  }
}

