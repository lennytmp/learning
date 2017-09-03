/**
 * Binary Search tree node implementation.
 */

package searchtree;


class Node {
  //Right child node.
  public Node rightChild;
  
  //Left child node.
  public Node leftChild;

  //Parent node.
  public Node parent;

  //Value of the node.
  public int value;


  /**
   * Node constructor, sets the value.
   * @param value Integer value of the node.
   */
  public Node(int value) {
    this.value = value;
  }


  /**
   * @return Number of children which are not null.
   */ 
  public int childNum() {
    if (leftChild != null && rightChild != null) {
      return 2;
    }
    if (leftChild != null || rightChild != null) {
      return 1;
    }
    return 0;
  }


  /**
   * Removes child from element. 
   * @param n Child node to remove.
   */
  public void removeChild(Node n) {
    if (leftChild == n) {
      leftChild = null;
    }
    if (rightChild == n) {
      rightChild = null;
    }
  }


  /**
   * Gets the child node if there's only one,
   * otherwise throws an exception.
   * @return Child node.
   */
  public Node getTheOnlyChild() {
    if (childNum() != 1) {
      throw new RuntimeException("More than one child");
    }
    if (leftChild != null) {
      return leftChild;
    } else {
      return rightChild;
    }
  }


  /**
   * Adds child to current node, updated child parent value.
   * @param n Child node to add.
   */
  public void addChild(Node n) {
    if (n.value < value && leftChild == null) {
      leftChild = n;
      leftChild.parent = this;
    }
    if (n.value >= value && rightChild == null) {
      rightChild = n;
      rightChild.parent = this;
    }
  }


  /**
   * Returns the node as a string including children's values.
   * @return String to print.
   */
  @Override
  public String toString() {
    String result = "";
    result = Integer.toString(value);
    if (leftChild != null) {
      result += " left: " + Integer.toString(leftChild.value);
    }
    if (rightChild != null) {
      result += " right: " + Integer.toString(rightChild.value);
    }
    return result; 
  }
}
