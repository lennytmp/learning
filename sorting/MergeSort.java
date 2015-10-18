/**
 * Performs Merge Sort algorithm.
 */


package sorting;
import java.util.Arrays;


public class MergeSort {
  
  // Stores the number of inversions.
  static long inversions = 0;

  /*
   * Sets an array, sorts it and prints input, result and number
   * of inversions. 
   * @param args Array of arguments for launching the program.
   * @export
   */
  public static void main(String[] args) {
    int[] arr = {95, 21, 12, 83, 1, 5};
    System.out.println("Input: " + Arrays.toString(arr));
    int[] result = sort(arr, 0, arr.length - 1);
    System.out.println("Result: " + Arrays.toString(result));
    System.out.println("Inversions: " + Long.toString(inversions));
  }


  /*
   * Recursively sorts an array of items. If the array
   * is larger than specified in params - truncates the array.
   * Also increments the number of inversions in MergeSort class.
   * Algorithm description: https://en.wikipedia.org/wiki/Quicksort
   * @param arr Array to sort.
   * @param from From which element sorting is needed.
   * @param to Till which element sorting is needed.
   * @return Array of sorted elements.
   * @export
   */
  public static int[] sort(int[] arr, int from, int to) {
    if ((to - from) > 1) {
      int l = from + Math.round((to - from) / 2);
      int[] left = MergeSort.sort(arr, from, l);
      int[] right = MergeSort.sort(arr, l, to);
      int i = 0, j = 0;
      int[] result = new int[to - from];
      for (int k = 0; k < result.length; k++) {
        if (i < left.length && (j >= right.length || left[i] <= right[j])) {
          result[k] = left[i];
          i++;
        } else {
          result[k] = right[j];
          MergeSort.inversions += left.length - i;
          j++;
        }
      }
      return result;
    }
    return new int[]{arr[from]};
  }
}
