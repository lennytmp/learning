package euler;

import linkedlist.LinkedList;

class Problem20 {
  public static void main(String[] args) {
    LinkedList<Integer> digits = getFaq(100);
    int sum = 0;
    while (digits != null) {
      sum += digits.value;
      digits = digits.next;
    }
    System.out.println(sum);
  }


  public static LinkedList<Integer> getFaq(int num) {
    LinkedList<Integer> digits = new LinkedList<Integer>(1);
    for (int i = 2; i <= num; i++) {
      int r = 0;
      LinkedList<Integer> current = digits;
      LinkedList<Integer> last = null;
      while (current != null) {
        //System.out.println(current.value + " " + r);
        int s = current.value * i + r;
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
