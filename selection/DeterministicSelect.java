/**
 * Performs Deterministic Select Algorithm.
 */


package selection;
import java.util.Arrays;
import sorting.MergeSort;


public class DeterministicSelect {


  //Stores the number of inversions.
  static long inversions = 0;
  
  //Length of small arrays to determine the median of medians.
  static int smLength = 5;



  /*
   * Sets an array, sorts it and prints input, result and number
   * of inversions. 
   * @param args Array of arguments for launching the program.
   * @export
   */
  public static void main(String[] args) {
    int[] arr = {98, 12, 1, 23, 5, 82, 11, 4, 8, 56};
    int order = 5;
    System.out.println("Input: " + Arrays.toString(arr));
    int result = select(arr, 0, arr.length - 1, order);
    System.out.print("Order: " + Integer.toString(order));
    System.out.println(" Result: " + Integer.toString(result));
    System.out.println("Inversions: " + Long.toString(inversions));
    System.out.println("Array state: " + Arrays.toString(arr));
  }


  /*
   * Recursively selects the element at order place from the array.
   * @param arr Array to select from.
   * @param from From which element selection is needed.
   * @param to Till which element seledction is needed.
   * @param order Order statistics. 
   * @return Integer the element corresponding to order statistics.
   */
  public static int select(int[] arr, int from, int to, int order) {
    if (to == from && order == 1) {
      return arr[from];
    }
    int pivot = getPivot(arr, from, to);
    pivot = getIndexByValue(arr, from, to, pivot);
    arr = swap(arr, from, pivot);
    int i = from + 1, j = from + 1;
    while (i <= to) {
      if (arr[i] < arr[from]) {
        arr = swap(arr, i, j);
        j++;
      }
      i++;
    }
    arr = swap(arr, from, j - 1);
    if ((j - from) < order) {
      return select(arr, j, to, order - (j - from));
    }
    if ((j - from) > order) {
      return select(arr, from, j - 2, order);
    }
    return arr[from + order - 1]; // j - 1 == order
  }


 /*
   * Searches for the element in array, returns it's index or -1.
   * @param arr Array to search in.
   * @param from From which element search is needed.
   * @param to Till which element search is needed.
   * @param value Value to search for. 
   * @return Index of the first element that is equal to value or -1 if not found.
   */
  private static int getIndexByValue(int[] arr, int from, int to, int value) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == value) {
        return i;
      }
    }
    return -1;
  }


  /*
   * Find a pivot by finding a median of medians from n/5 arrays, by sorting
   * them first. 
   * @param arr Array to select from.
   * @param from From which element selection is needed.
   * @param to Till which element seledction is needed.
   * @return Integer the element corresponding to order statistics.
   */
  private static int getPivot(int[] arr, int from, int to) {
    int[] medians = new int[divideRoundUp(to - from, smLength)];
    for (int i = from; (double)(i - from)/smLength < medians.length; i += smLength) {
  
      int smTo = Math.min(i + smLength - 1, to);
      arr = sorting.QuickSort.sort(arr, i, smTo);

      int medianPos = i + divideRoundUp(smTo - i, 2);
      medians[divideRoundUp(i - from, smLength)] = arr[medianPos];

    }
    return select(medians, 0, medians.length - 1, divideRoundUp(medians.length, 2)); 
  }
  
  /*
   * Divides two ints, rounds up the result and returns it as int. 
   * @param a Numerator.
   * @param b Denumerator.
   * @return Int rounded up value of a/b. 
   */
  private static int divideRoundUp(int a, int b) {
    return (int)Math.ceil((double)a / b);
  }


  /*
   * Swaps to elements in an array. Increases the number of inversions.
   * @param arr Array to make a swap in.
   * @param pos1 Position of the first element.
   * @param pos2 Position of the second element.
   * @return Array with swapped elements. 
   */
  private static int[] swap(int[] arr, int pos1, int pos2) {
    if (pos1 == pos2) {
      return arr;
    }
    inversions += Math.abs(pos2 - pos1);
    int tmp = arr[pos1];
    arr[pos1] = arr[pos2];
    arr[pos2] = tmp;
    return arr;
  }
}
