/**
 * Implementation of the Linked List class.
 */

package linkedlist;


//Class for storing linked list.
class LinkedList {
  // Value of the node
  int value;

  // Link to the next element
  public LinkedList next;

  /**
   * Constructor of linked list.
   * @param value The value of the first node.
   */
  public LinkedList(int value) {
    this.value = value;
  }


  /**
   * Adds new value to the linked list by
   * adding to in front of all other values.
   * @param value The value to add.
   */
  public void add(int value) {
    LinkedList second = new LinkedList(this.value);
    second.next = this.next;
    this.value = value;
    this.next = second;
  }

  /**
   * Returning printable version of the linked list.
   */
  @Override
  public String toString() {
    if (this.next == null) {
      return Integer.toString(this.value);
    } else {
      return Integer.toString(this.value) + " -> " + this.next;
    }
  }
}

