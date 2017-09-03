package euler;

import java.util.HashMap;
import java.util.HashSet;

class Problem21 {
  static HashMap<Integer, Integer> saved = new HashMap<Integer, Integer>();

  static int getDivisorsSum(int num) {
    if (saved.get(num) != null) {
      return saved.get(num);
    }
    int sum = 1;
    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        sum += i + num / i;
      }
    }
    saved.put(num, sum);
    return sum;
  }


  public static void main(String[] args) {
    HashSet<Integer> amicables = new HashSet<Integer>();
    for (int i = 2; i <= 10000; i++) {
      int tmp = getDivisorsSum(i);
      if (i == getDivisorsSum(tmp)) {
        System.out.println(i + " - " + tmp);
        if (tmp != i) {
          amicables.add(tmp);
          amicables.add(i);
        }
      }
    }
    int sum = 0;
    for (Integer a : amicables) {
      sum += a;
    }
    System.out.println("Sum: " + sum); 
  }
}
