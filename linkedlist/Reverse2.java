/**
 * Implementation of reversing the linked list.
 */

package linkedlist;


public class Reverse2 {

  /**
   * Sets a linked list, prints it, calls reverse function and prints
   * the result.
   * @param args Array of arguments for launching the program.
   */
  public static void main(String[] args) {
    LinkedList a = new LinkedList(1);
    a.next = new LinkedList(2);
    a.next.next = null;
    System.out.println(a);
    a = reverse(a, null);
    System.out.println(a);
  }


  /**
   * Recursively reverses the linked list.
   * @param list The list to reverse.
   * @param reminder Part of the list to be added in the end of the reversed
   * part. In the first run should be null.
   */
	static LinkedList reverse(LinkedList list, LinkedList reminder) {
    if (list == null) {
      return reminder;
    }
    LinkedList tmp = list.next.next;
    list.next.next = list;
    list = list.next;
    list.next.next = reminder;
    return reverse(tmp, list);
  }
}

