/**
 * Implements finding cycle in a linked list algorithm.
 */

package linkedlist;

import java.util.HashSet;


class FindCycle {

  
  /**
   * Checks if list has a cycle by changing it's values.
   * @param list The list to search cycle in.
   * @return True if there is a cycle, false otherwise.
   */
  public static boolean hasCycleWithChanges(LinkedList<Integer> list) {
    if (list == null) {
      return false;
    }
    if (list.value == -1) {
      return true;
    }
    list.value = -1;
    return hasCycleWithChanges(list.next);
  }


  /**
   * Checks if list has a cycle by comparing next link to
   * the previous elements.
   * @param list The list to search cycle in.
   * @param current The current element, by default the same as list.
   * @return True if there is a cycle, false otherwise.
   */
  public static boolean hasCycle1(LinkedList list, LinkedList current) {
    if (current == null || current.next == null) {
      return false;
    }
    if (current == current.next) {
      return true;
    }
    LinkedList cur = list;
    while (cur != current) {
      if (current.next == cur) {
        return true;
      }
      cur = cur.next;
    }
    return hasCycle1(list, current.next);
  }


  /**
   * Checks if list has a cycle by storing all elements in a hashset. 
   * @param list The list to search cycle in.
   * @return True if there is a cycle, false otherwise.
   */
  public static boolean hasCycle2(LinkedList list) {
    if (list == null) {
      return false;
    }
    HashSet<LinkedList> seen = new HashSet<LinkedList>();
    if (seen.contains(list)) {
      return true;
    }
    seen.add(list);
    return hasCycle2(list.next, seen);
  }


  /**
   * Checks if list has a cycle by storing all elements in a hashset;
   * private function which helps function hasCycle2 with one parameter.
   * @param list The list to search cycle in.
   * @param seen The HashSet with all items observed. 
   * @return True if there is a cycle, false otherwise.
   */
  private static boolean hasCycle2(LinkedList list, HashSet<LinkedList> seen) {
    if (list == null) {
      return false;
    }
    if (seen.contains(list)) {
      return true;
    }
    seen.add(list);
    return hasCycle2(list.next, seen);
  }


  /**
   * Checks if list has a cycle by having two pointers.
   * @param list The list to search cycle in.
   * @return True if there is a cycle, false otherwise.
   */
  public static boolean hasCycle3(LinkedList list) {
    if (list == null || list.next == null) {
      return false;
    }
    LinkedList slow = list.next;
    LinkedList fast = list.next.next;
    while (slow != fast) {
      if (slow.next == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
  }
}
