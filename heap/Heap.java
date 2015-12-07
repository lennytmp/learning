package heap;
import java.util.ArrayList;
import java.util.Arrays;

class Node {
  public int value;
  public Node[] children = new Node[2];
  public Node parent;
  

  public Node(int value) {
    this.value = value;
  }


  public int getChildrenNum() {
    int i = 0;
    for (Node el : children) {
      if (el != null) i++;
    }
    return i;
  }


  public void addChild(Node child) {
    for (int i = 0; i < children.length; i++) {
      if (children[i] == null) {
        children[i] = child;
        child.parent = this;
        return;
      }
    }
    throw new RuntimeException("Trying to add child to full node");
  }


  @Override
  public String toString() {
    return Integer.toString(this.value);
  }
}


public class Heap {
  ArrayList<Node> elements = new ArrayList();
  Node root;

  public void add(Node element) {
    if (root == null) {
      root = element;
    } else {
      Node el = getFirstChildless(); 
      el.addChild(element);
    }
    this.elements.add(element);
  }

  @Override
  public String toString() {
    String result = "";
    int currentLevel = 0;

    class ExtNode {
      public int level;
      public Node node;

      public ExtNode(Node node, int level) {
        this.level = level;
        this.node = node;
      }
    }

    ArrayList<ExtNode> queue = new ArrayList();
    queue.add(new ExtNode(root, 0));
    while (queue.size() > 0) {
      ExtNode extNode = queue.remove(0);
      if (currentLevel < extNode.level) {
        result += "\n";
      }
      currentLevel = extNode.level;
      Node element = extNode.node;
      if (element == null) {
        continue;
      }
      result += element.toString() + "   ";
      for (Node ch : element.children) {
        if (ch != null) {
          queue.add(new ExtNode(ch, extNode.level + 1));
        }
      }
    }
    return result;
  }


  public Node getFirstChildless() {
    ArrayList<Node> queue = new ArrayList();
    queue.add(root);
    while (queue.size() > 0) {
      Node element = queue.remove(0);
      if (element.getChildrenNum() < 2) {
        return element;
      }
      queue.addAll(Arrays.asList(element.children));
    }
    throw new RuntimeException("No childless element found");
  }


  public static void main(String[] args) {
    Heap tree = new Heap();
    for (int i = 0; i < 10; i++) {
      tree.add(new Node(i));
    }
    System.out.println(tree);
  }
}
