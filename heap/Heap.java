package heap;
import java.util.ArrayList;
import java.util.Arrays;

class Node {
  public int value;
  public int index;
  
  public int level;
  
  public Node[] children = new Node[2];
  public Node parent;
  

  public Node(int value, int index) {
    this.value = value;
    this.index = index;
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
        child.level = this.level + 1;
        return;
      }
    }
    throw new RuntimeException("Trying to add child to full node");
  }


  public void removeChild(Node child) {
    for (int i = 0; i < children.length; i++) {
      if (children[i] == child) {
        child.parent = null;
        children[i] = null;
      }
    }
  }


  public ArrayList<Node> getChildren() {
    ArrayList<Node> result = new ArrayList();
    for (Node ch : children) {
      if (ch != null) {
        result.add(ch);
      }
    }
    return result; 
  }

  public Node getMaxChild() {
    Node result = children[0];
    for (Node ch : children) {
      if (ch != null && ch.value > result.value) {
        result = ch;
      }
    }
    return result; 
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
      root.level = 0;
    } else {
      Node el = getFirstFreeParent(); 
      el.addChild(element);
      heapify(element);
    }
    this.elements.add(element);
  }


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


  public void remove(int index) {
    Node elem = findByIndex(index);
    if (elem == null) return;
    Node last = getLastElement();
    if (elem == last) {
      elem.parent.removeChild(elem);
      return;
    }
    Node lastParent = last.parent; 
    if (elem.parent != null) {
      Node p = elem.parent;
      p.removeChild(elem);
      p.addChild(last);
    } else {
      root = last;
    }
    for (int i = 0; i < elem.children.length; i++) {
      if (elem.children[i] != null) {
        elem.children[i].parent = last;
      }
    }
    Node[] children = elem.children;
    last.children = children;
    last.level = elem.level;
    lastParent.removeChild(last);
   
    ArrayList<Node> queue = new ArrayList();
    queue.add(last);
    while (queue.size() > 0) {
      Node node = queue.remove(0);
      Node maxChild = node.getMaxChild();
      if (maxChild != null) {
        if (maxChild.value > node.value) {
          int tmp = maxChild.parent.value;
          maxChild.parent.value = maxChild.value;
          maxChild.value = tmp;
          tmp = maxChild.parent.index;
          maxChild.parent.index = maxChild.index;
          maxChild.index = tmp;
          queue.add(maxChild);
        }
      }
    } 
  }


  public void heapify(Node elem) {
    if (elem.parent != null && elem.parent.value < elem.value) {
      int tmp = elem.parent.value;
      elem.parent.value = elem.value;
      elem.value = tmp;
      tmp = elem.parent.index;
      elem.parent.index = elem.index;
      elem.index = tmp;
      heapify(elem.parent);
    }
  }


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


  public static void main(String[] args) {
    Heap tree = new Heap();
    for (int i = 0; i < 10; i++) {
      tree.add(new Node(i, i));
    }
    System.out.println(tree);
    tree.remove(3);
    System.out.println(tree);
  }
}
