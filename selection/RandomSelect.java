/**
 * Performs Random Select Algorithm.
 */


import java.util.Arrays;
import java.util.Random;


public class RandomSelect {


  // Generator of random numbers.
  Random generator = new Random();

  // Stores the number of inversions.
  static long inversions = 0;



  /*
   * Sets an array, sorts it and prints input, result and number
   * of inversions.
   * @param args Array of arguments for launching the program.
   * @export
   */
  public static void main(String[] args) {
    int[] arr = {98, 12, 1, 23, 5, 82, 11, 4, 8, 56};
    int order = 5; // 12
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
    Random generator = new Random();
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
    if ((j - from) < order) {
      return select(arr, j, to, order - (j - from));
    }
    if ((j - from) > order) {
      return select(arr, from, j - 2, order);
    }
    return arr[from + order - 1]; // j - 1 == order
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
