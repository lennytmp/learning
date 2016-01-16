package euler;

import linkedlist.LinkedList;

class Problem16 {
  public static void main(String[] args) {
    LinkedList<Integer> digits = get2PoweredRev(1000);
    int sum = 0;
    while (digits != null) {
      sum += digits.value;
      digits = digits.next;
    }
    System.out.println(sum);
  }


  public static LinkedList<Integer> get2PoweredRev(int power) {
    LinkedList<Integer> digits = new LinkedList<Integer>(2);
    for (int i = 1; i < power; i++) {
      int r = 0;
      LinkedList<Integer> current = digits;
      LinkedList<Integer> last = null;
      while (current != null) {
        //System.out.println(current.value + " " + r);
        int s = current.value * 2 + r;
        r = 0;
        current.value = s % 10;
        if (s >= 10) {
          r = s/10;
        }
        last = current;
        current = current.next;
      }
      while (r > 0) {
        current = new LinkedList<Integer>(r % 10);
        last.next = current;
        last = current;
        r = r / 10;
      } 
    }
    return digits;
  }
}
