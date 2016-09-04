import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FindCycle2 {
  public static void main(String[] args) {
    FcNode root = new FcNode(0),
      a = new FcNode(1),
      b = new FcNode(2),
      c = new FcNode(3),
      d = new FcNode(4);
    root.addChild(a);
    root.addChild(b);
    a.addChild(c);
    b.addChild(a);
    c.addChild(d);
    System.out.println(isCycled(root));
  }

  static boolean isCycled(FcNode root) {
    Deque<Tuple<FcNode>> stack = new ArrayDeque<>();
    Set<FcNode> visited = new HashSet<>();
    stack.add(new Tuple<FcNode>(root, null));
    visited.add(root);
    while (stack.size() > 0) {
      Tuple t = stack.removeLast();
      FcNode cur = (FcNode)t.first;
      FcNode comingFrom = (FcNode)t.second;
      for (FcNode c : cur.children) {
        if (!c.equals(comingFrom) && visited.contains(c)) {
          return true;
        }
        if (!c.equals(comingFrom)) {
          stack.add(new Tuple<FcNode>(c, cur));
          visited.add(c);
        }
      }
    }
    return false;
  }
}

class FcNode {
  List<FcNode> children = new ArrayList<>();
  int id;

  FcNode(int id) {
    this.id = id;
  }

  void addChild(FcNode n) {
    children.add(n);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    // TODO: make a thorough safe check instead.
    FcNode n = (FcNode)o;
    return n.id == id;
  }

  @Override
  public int hashCode() {
    return id;
  }
}

class Tuple<T> {
  T first;
  T second;

  Tuple(T a, T b) {
    first = a;
    second = b;
  }
}
