import java.util.ArrayDeque;
import java.util.Deque;

public class MathTree {
  public static void main(String[] args) {
    Node r1 = new Node("*");
    r1.left = new Node("+");
    r1.left.left = new Node("a");
    r1.left.right = new Node("b");
    r1.right = new Node("+"); 
    r1.right.left = new Node("c");
    r1.right.right = new Node("d"); // (a+b)*(c+d)

    Node r2 = new Node("+");
    r2.left = new Node("*");
    r2.left.left = new Node("c");
    r2.left.right= new Node("b");
    r2.right = new Node("*");
    r2.right.left = new Node("c");
    r2.right.right = new Node("a"); // c*b + c*a
    System.out.println(isEq(r1, r2));
    reformatTree(r1);
    reformatTree(r2);
    System.out.println(isEq(r1, r2));
  }

  static boolean isEq(Node r1, Node r2) {
    System.out.println(r1.toString() + " " + r2.toString());
    return r1.toString().equals(r2.toString());
  }
  
  static void reformatTree(Node head) {
    Deque<Node> queue = new ArrayDeque<>();
    queue.addLast(head);
    while (queue.size() > 0) {
      Node n = queue.removeFirst();
      if (n.value.equals("*")) {
        boolean left = true;
        for (Node c : new Node[] {n.left, n.right}) {
          if (c.value.equals("+")) {
            Node another = left ? n.right : n.left;
            n.value = "+";
            Node newChild1 = new Node("*");
            newChild1.left = another;
            newChild1.right = c.left;
            Node newChild2 = new Node("*");
            newChild2.left = another;
            newChild2.right = c.right;
            n.left = newChild1;
            n.right = newChild2;
            break;
          }
          left = false;
        }
      }
      if (n.left != null && n.right != null) {
        queue.addLast(n.left);
        queue.addLast(n.right);
      }
    }
  }
}

class Node {
  Node left, right;
  String value;

  Node(String val) {
    this.value = val;
  }

  @Override
  public String toString() {
    if (value.equals("+") || value.equals("*")) {
      String l = left.toString();
      String r = right.toString();
      if (l.compareTo(r) < 0) {
        return value + l + r;
      } else {
        return value + r + l;
      }
    } else {
      return this.value;
    }
  }
}

