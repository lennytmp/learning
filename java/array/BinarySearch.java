/**
 * Implements binary search algorithm.
 */
package array;

import java.util.Arrays;
import java.lang.Math;

class BinarySearch {


  /**
   * Sets an array, and searches for an element in it.
   * @param args Program arguments, not used.
   */
  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 23, 24, 30, 64};
    System.out.println(Arrays.toString(arr));
    int index = search(arr, 5, 0, arr.length - 1);
    System.out.println("5 is found at position " + index);
  }


  /**
   * Searches in array for the given value and returns
   * it's index or -1.
   * @param arr The array to search in.
   * @param value The value to search for.
   * @param from The index to start search from.
   * @param to The index to finish search at.
   * @return Index of the element with the value or -1 if not found.
   */
  public static int search(int[] arr, int value, int from, int to) {
    if (from > to || from < 0 || to >= arr.length) {
      return -1;
    }
    int middle = Math.round(from + (to - from)/2);
    if (value == arr[middle]) {
      return middle;
    }
    if (value < arr[middle]) {
      return search(arr, value, from, middle - 1);
    } else {
      return search(arr, value, middle + 1, to);
    }    
  }
}
