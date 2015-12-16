package searchtree;

class Node {
  public Node rightChild;
  public Node leftChild;
  public Node parent;
  public int value;

  public Node(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value) + "\nleft: " + leftChild +
       "\nright: " + rightChild;
  }
}
