/**
 * Linked List class and class to reverse the linked list. 
 */

package linkedlist;


//Class for storing linked list.
class LinkedList {
	int value;
	LinkedList next;
	LinkedList(int value) {
		this.value = value;
  }
}


public class Reverse {

  /**
   * Sets a linked list, prints it, calls reverse function and returns
   * the result. 
   * @param args Array of arguments for launching the program.
   * @export
   */
  public static void main(String[] args) {
    LinkedList a = new LinkedList(5);
    a.next = new LinkedList(10);
    a.next.next = new LinkedList(2);
    a.next.next.next = new LinkedList(1);
    printList(a);
    a = reverseLinkedList(a, null);
    printList(a);
  }

  /**
   * Recursively reverses the linked list. 
   * @param list The list to reverse.
   * @param left Part of the list to be added in the end of the reversed
   * part. In the first run should be null.
   */
  static LinkedList reverseLinkedList(LinkedList list, LinkedList left) {
    if (list.next == null) {
      list.next = left;
      return list;
    }
      LinkedList p2 = list.next.next;
      list.next.next = list;
      list = list.next;
      list.next.next = left;
      if (p2 == null) {	
        return list;
    }
    return reverseLinkedList(p2, list);
  }

  
  /**
   * Prints the linked list
   * @param list The list to print.
   */
  public static void printList(LinkedList list) {
    System.out.print(list.value);
    if (list.next != null) {
      System.out.print(" -> ");
      printList(list.next);
    } else {
      System.out.println("");
    }
  }
}
