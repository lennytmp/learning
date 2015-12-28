package linkedlist;

import java.util.Random;

public class FindCycleTest extends junit.framework.TestCase {
  private static final Random rnd = new Random();

  public static LinkedList<Integer> generateCycled() {
    LinkedList<Integer> result = new LinkedList<Integer>();
    int length = rnd.nextInt(4) + 2;
    LinkedList<Integer> last = result;
    for (int i = 0; i < length; i++) {
      result.push(rnd.nextInt(10));
      if (last.next != null) {
        last = last.next;
      }
    }
    length = rnd.nextInt(length) + 1;
    int i = 0;
    LinkedList cur = result;
    while (i < length) {
      if (cur.next != null) {
        cur = cur.next;
      }
      i++;
    }
    last.next = cur;
    return result;
  }


  public static LinkedList<Integer> smallesCycle() {
    LinkedList<Integer> a = new LinkedList<Integer>(2);
    a.next = a;
    return a;
  }


  public void testHasCycleWithChanges() {
    LinkedList<Integer> a = generateCycled(); 
    assertEquals("has cycle in a random graph",
      FindCycle.hasCycleWithChanges(a),
      true);
    a = new LinkedList<Integer>(2);
    assertEquals("doesn't have a cycle",
      FindCycle.hasCycleWithChanges(a),
      false);
    a = smallesCycle();
    assertEquals("has cycle in smallest cycled list",
      FindCycle.hasCycleWithChanges(a),
      true);
  }
  

  public void testHasCycle1() {
    LinkedList<Integer> a = smallesCycle();
    assertEquals("has cycle in smallest cycled list",
      FindCycle.hasCycle1(a, a),
      true);
    a = new LinkedList<Integer>(2);
    assertEquals("doesn't have a cycle",
      FindCycle.hasCycle1(a, a),
      false);
    a = generateCycled(); 
    assertEquals("has cycle in a random graph",
      FindCycle.hasCycle1(a, a),
      true);
  }


  public void testHasCycle2() {
    LinkedList<Integer> a = smallesCycle();
    assertEquals("has cycle in smallest cycled list",
      FindCycle.hasCycle2(a),
      true);
    a = new LinkedList<Integer>(2);
    assertEquals("doesn't have a cycle",
      FindCycle.hasCycle2(a),
      false);
    a = generateCycled(); 
    assertEquals("has cycle in a random graph",
      FindCycle.hasCycle2(a),
      true);
  }
}
