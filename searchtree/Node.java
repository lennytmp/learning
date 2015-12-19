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
