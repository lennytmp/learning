package euler;

import java.util.Arrays;

class Problem24 {
  public static Long getNumByDig(int[] digits) {
    Long n = new Long(0);
    for (int k = 0; k < digits.length; k++) {
      n += (long)Math.pow(10, digits.length - k - 1) * digits[k];
    }
    return n;
  }


  public static int[] sort(int[] arr, int from, int to) {
    if (from == to) {
      return arr;
    }
    int[] result = Arrays.copyOf(arr, arr.length);
    int middle = from + (to-from) / 2;
    int[] left = sort(arr, from, middle);
    int[] right = sort(arr, middle + 1, to);
    int leftCounter = from;
    int rightCounter = middle + 1;
    for (int i = from; i <= to; i++) {
      if (rightCounter > to) {
        result[i] = left[leftCounter];
        leftCounter++;
        continue;
      }
      if (leftCounter > to) {
        result[i] = right[rightCounter];
        rightCounter++;
        continue;
      }
      if (left[leftCounter] < right[rightCounter]) {
        result[i] = left[leftCounter];
        leftCounter++;
      } else {
        result[i] = right[rightCounter];
        rightCounter++;
      }
    }
    return result;
  }


  public static int[] getNext(int[] digits) {
    int[] result = Arrays.copyOf(digits, digits.length);
    int toChange = -1;
    int[] empty = {};
    for (int i = digits.length - 2; i >= 0; i--) {
      if (digits[i] < digits[i+1]) {
        toChange = i;
        break;
      }
    }
    if (toChange == -1) {
      return empty;
    }
    boolean found = false;
    int minIndex = -1, minVal = -1;
    for (int i = digits.length - 1; i > toChange; i--) {
      if (digits[i] > digits[toChange]) {
        if (!found || digits[i] < minVal) {
          found = true;
          minIndex = i;
          minVal = digits[i];
        } 
      }
      if (digits[i] < minVal && digits[i] > digits[toChange]) {
        minIndex = i;
        minVal = digits[i];
      }
    }
    int tmp = result[toChange];
    result[toChange] = result[minIndex];
    result[minIndex] = tmp;
    return sort(result, toChange + 1, digits.length - 1);
  }


  public static void main(String[] args) {
    int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] cur = digits;
    System.out.println("1: " + getNumByDig(cur));
    Long it = new Long(2);
    while (cur.length > 0) {
      cur = getNext(cur);
      if (it == 1000000) {
        System.out.println(it + ": " + getNumByDig(cur));
        break;
      }
      it++;
    }
  }
}
