/**
 * Implementation of the Linked List class.
 */

package linkedlist;


//Class for storing linked list.
public class LinkedList<K> {
  // Value of the node
  public K value = null;

  public int size = 0;

  // Link to the next element
  public LinkedList<K> next;

  /**
   * Constructor of linked list.
   * @param value The value of the first node.
   */
  public LinkedList(K value) {
    this.value = value;
    size = 1;
  }


  /**
   * Constructor of linked list.
   * @param value The value of the first node.
   */
  public LinkedList() {}



  /**
   * Adds new value to the linked list by
   * adding to in front of all other values.
   * @param value The value to add.
   */
  public void push(K value) {
    size++;
    if (size == 1) {
      this.value = value;
      return;
    }
    LinkedList<K> second = new LinkedList<K>(this.value);
    second.next = this.next;
    this.value = value;
    this.next = second;
  }


  /**
   * Removes the first element and returns it's value.
   * @return Value of the top element.
   */
  public K pop() {
    K result = this.value;
    size--;
    if (this.next == null) {
      this.value = null;
      return result;
    }
    this.value = this.next.value;
    this.next  = this.next.next;
    return result;
  }

  /**
   * Returning printable version of the linked list.
   */
  @Override
  public String toString() {
    if (this.value == null) {
      return "null";
    }
    if (this.next == null) {
      return this.value.toString();
    } else {
      return this.value + " -> " + this.next;
    }
  }
}

