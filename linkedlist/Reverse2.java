class LinkedList {
	LinkedList next;
	int value;
	LinkedList(int value, LinkedList next) {
		this.next = next;
		this.value = value;
}
}


public class Reverse2 {
  public static void main(String[] args) {
    LinkedList a = new LinkedList(1, new LinkedList(2, null)); 
    a = reverse(a, null);
    while (a != null) {
      System.out.print(Integer.toString(a.value) + " -> ");
      a = a.next;
    }
  }


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

