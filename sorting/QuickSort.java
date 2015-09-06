/**
 * Performs Quick Sort algorithm,
 */


package sorting;
import sorting.Sort;
import java.util.Random;


public class QuickSort extends Sort {


  // Generator of random numbers.
  Random generator = new Random();


  /*
   * Recursively sorts an array of items.
   * Algorithm description: https://en.wikipedia.org/wiki/Quicksort
   * @param arr Array to sort.
   * @param from From which element sorting is needed.
   * @param to Till which element sorting is needed.
   * @return Array of sorted elements.
   */
  public static int[] sort(int[] arr, int from, int to) {
    if (to == from + 1 || to <= from) {
      return arr;
    }
    Random generator = new Random();
    arr = swap(arr, from, from + generator.nextInt(to - from));
    int i = from + 1, j = from + 1;
    while (i < to) {
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


  /*
   * Swaps to elements in an array. Increases the number of inversions.
   * @param arr Array to make a swap in.
   * @param pos1 Position of the first element.
   * @param pos2 Position of the second element.
   * @return Array with swapped elements. 
   */
  public static int[] swap(int[] arr, int pos1, int pos2) {
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
