/**
 * Performs Quick Sort algorithm.
 */


package sorting;
import java.util.Arrays;
import java.util.Random;


public class QuickSort {


  // Generator of random numbers.
  static Random generator = new Random();

  // Stores the number of inversions.
  static long inversions = 0;



  /**
   * Sets an array, sorts it and prints input, result and number
   * of inversions. 
   * @param args Array of arguments for launching the program.
   * @export
   */
  public static void main(String[] args) {
    int[] arr = {82, 11, 4, 8, 56};
    System.out.println("Input: " + Arrays.toString(arr));
    int[] result = sort(arr, 0, arr.length - 1);
    System.out.println("Result: " + Arrays.toString(result));
    System.out.println("Inversions: " + Long.toString(inversions));
  }


  /**
   * Recursively sorts an array of items.
   * Algorithm description: https://en.wikipedia.org/wiki/Quicksort
   * @param arr Array to sort.
   * @param from From which element sorting is needed.
   * @param to Till which element sorting is needed.
   * @return Array of sorted elements.
   */
  public static int[] sort(int[] arr, int from, int to) {
    if (to <= from) {
      return arr;
    }
    arr = swap(arr, from, from + generator.nextInt(to - from));
    int i = from + 1, j = from + 1;
    while (i <= to) {
      if (arr[i] < arr[from]) {
        arr = swap(arr, i, j);
        j++;
      }
      i++;
    }
    arr = swap(arr, from, j - 1);
    arr = sort(arr, from, j - 1);
    arr = sort(arr, j, to);
    return arr;
  }


  /**
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
