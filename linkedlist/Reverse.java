/**
 * Implementation of reversing the linked list.
 */

package linkedlist;


public class Reverse {

  /**
   * Sets a linked list, prints it, calls reverse function and prints
   * the result.
   * @param args Array of arguments for launching the program.
   */
  public static void main(String[] args) {
    LinkedList a = new LinkedList(5);
    a.next = new LinkedList(10);
    a.next.next = new LinkedList(2);
    a.next.next.next = new LinkedList(1);
    System.out.println(a);
    
    LinkedList reversed = reverse1(a, null);
    System.out.println(reversed);
    
    reversed = reverse2(reversed, null);
    System.out.println(reversed);

    reversed = reverse3(reversed, null);
    System.out.println(reversed);
  }

  /**
   * Recursively reverses the linked list.
   * @param list The list to reverse.
   * @param reversed Already reversed part. 
   * @return Reversed linked list.
   */
  public static LinkedList reverse1(LinkedList list, LinkedList reversed) {
    if (list.next == null) {
      list.next = left;
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
   * @param reminder Linked list left to reverse.
   * @param reversed Already reversed part. 
   * @return Reversed linked list.
   */
  public static LinkedList reverse3(LinkedList reminder, LinkedList reversed) {
    LinkedList tmp = null;
    if (reminder.next != null) {
      tmp = reminder.next;
      reminder.next = reversed;
    } else {
      reminder.next = reversed;
      return reminder;
    }
    return reverse3(tmp, reminder);
  }
}
