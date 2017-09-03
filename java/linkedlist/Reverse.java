/**
 * Implements reversing the linked list.
 */

package linkedlist;


public class Reverse {

  static int recursiveCalls = 0;


  /**
   * Sets a linked list, prints it, calls reverse function and prints
   * the result.
   * @param args Array of arguments for launching the program.
   */
  public static void main(String[] args) {
    LinkedList a = new LinkedList(0);
    for (int i = 1; i < 50; i++) {
      a.push(i);
    }
    System.out.println(a);
    
    LinkedList reversed = reverse1(a, null);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);
    
    recursiveCalls = 0;
    reversed = reverse2(reversed, null);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);

    recursiveCalls = 0;
    reversed = reverse3(reversed, null);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);

    recursiveCalls = 0;
    reversed = reverse4(reversed);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);

    recursiveCalls = 0;
    reversed = reverse5(reversed);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);

    recursiveCalls = 0;
    reversed = reverse6(reversed);
    System.out.println(reversed);
    System.out.println("Recursive calls: " + recursiveCalls);

    reversed = reverse7(reversed);
    System.out.println(reversed);
  }

  /**
   * Recursively reverses the linked list.
   * @param list The list to reverse.
   * @param reversed Already reversed part. 
   * @return Reversed linked list.
   */
  public static LinkedList reverse1(LinkedList list, LinkedList reversed) {
    recursiveCalls++;
    if (list.next == null) {
      list.next = reversed;
      return list;
    }
    LinkedList p2 = list.next.next;
    list.next.next = list;
    list = list.next;
    list.next.next = reversed;
    if (p2 == null) {  
        return list;
    }
    return reverse1(p2, list);
  }

 
  /**
   * Recursively reverses the linked list.
   * @param list The list left to reverse.
   * @param reversed Already reversed part. 
   * @return Reversed linked list.
   */
  public static LinkedList reverse2(LinkedList list, LinkedList reversed) {
    recursiveCalls++;
    if (list == null) {
      return reversed;
    }
    LinkedList tmp = list.next.next;
    list.next.next = list;
    list = list.next;
    list.next.next = reversed; 
    return reverse2(tmp, list);
  }


  /**
   * Recursively reverses the linked list.
   * @param remainder Linked list left to reverse.
   * @param reversed Already reversed part. 
   * @return Reversed linked list.
   */
  public static LinkedList reverse3(LinkedList remainder, LinkedList reversed) {
    recursiveCalls++;
    LinkedList tmp = null;
    if (remainder.next != null) {
      tmp = remainder.next;
      remainder.next = reversed;
    } else {
      remainder.next = reversed;
      return remainder;
    }
    return reverse3(tmp, remainder);
  }


  /**
   * Recursively reverses the linked list.
   * @param a Linked list to reverse.
   * @return Reversed linked list.
   */
  public static LinkedList reverse4(LinkedList a) {
    recursiveCalls++;
    if (a == null) {
      return null;
    }
    LinkedList head = reverse4(a.next);
    if (head == null) {
      return a;
    }
    LinkedList i;
    for (i = head; i.next != null; i = i.next);
    i.next = a;
    a.next = null;
    return head;
  }


  /**
   * Reverses the linked list without recursion.
   * @param list Linked list to reverse.
   * @return Reversed linked list.
   */
  public static LinkedList reverse5(LinkedList list) {
    recursiveCalls++;
    if (list == null) {
      return null;
    }
    LinkedList last = null, next = null;
    for (LinkedList a = list; a != null; a = next) {
      next = a.next;
      a.next = last;
      last = a;
    }
    return last;
  }



  /**
   * Recursively reverses the linked list.
   * @param a Linked list to reverse.
   * @return Reversed linked list.
   */
  public static LinkedList reverse6(LinkedList a) { 
    recursiveCalls++;
    if (a == null) {
      return null;
    }
    LinkedList last = a.next;
    LinkedList head = reverse6(a.next);
    if (head == null) {
      return a;
    }
    last.next = a;
    a.next = null;
    return head;
  }


  public static LinkedList reverse7(LinkedList input) {
    if (input  == null) {
      return null;
    }
    LinkedList cur = input;
    LinkedList tmp = null;
    LinkedList result = null;
    while (true) {
      tmp = cur.next;
      cur.next = result;
      result = cur;
      cur = tmp;
      if (tmp == null) {
        return result;
      }
    }
  }
}
