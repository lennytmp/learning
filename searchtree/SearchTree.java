package searchtree;

public class SearchTree {
  Node root;


  public static void main(String[] args) {
    SearchTree tree = new SearchTree();
    tree.add(10);
    tree.add(20);
    tree.add(1);
    System.out.println(tree);
  }


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

  @Override
  public String toString() {
    return root.toString();
  }
}
