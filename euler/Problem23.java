package euler;

import java.util.HashSet;

class Problem23 {
  static HashSet<Integer> abundants = new HashSet<Integer>();

  static int getDivisorsSum(int num) {
    int sum = 1;
    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        if (i == num / i) {
          sum += i;
        } else {
          sum += i + num / i;
        }
      }
    }
    return sum;
  }


  static boolean isSumOfTwo(int num) {
    for (Integer i : abundants) {
      if (abundants.contains(num - i)) {
        return true;
      }
    }
    return false;
  }


  public static void main(String[] args) {
    for (int i = 1; i < 28123; i++) {
      int s = getDivisorsSum(i);
      if (s > i) {
        abundants.add(i);
      }
    }
    int s = 0;
    for (int i = 1; i < 28123; i++) {
      if (!isSumOfTwo(i)) {
        s += i;
      }
    }
    System.out.println(s);
  }
}
