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

