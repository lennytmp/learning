/**
 * Performs Merge Sort algorithm,
 * Calculates the number of inversions,
 * Run instructions: java MergeSort path/to/file.txt
 */


package mergeSort;
import utils.Utils;


public class MergeSort {
  // Debug specifies if input and output should be printed.
  static boolean debug = false;


  // Stores the number of inversions.
  static long inversions = 0;


  /*
   * Checks arguments to see if file path is specified,
   * Prints input, output, number of inversions and time spent.
   * @param args Array of arguments for launching the program.
   */
  public static void main(String[] args) {
    int[] arr = new int[]{};
    if (args.length > 0) {
      arr = Utils.readFromFile(args[0]);
    }
    if (arr.length == 0) {
      arr = Utils.generateArray(5000000, 5000000);
    }
    long t = System.nanoTime();
    if (MergeSort.debug) {
      System.out.print("Input: ");
      Utils.printArr(arr);
    }

    int[] arr2 = MergeSort.sort(arr, 0, arr.length);
    if (MergeSort.debug) {
      System.out.print("Result: ");
      Utils.printArr(arr2);
    }
    int time = Math.round((System.nanoTime() - t)/1000000);
    System.out.println("Number of inversions " + Long.toString(inversions));
    System.out.println("Time elapsed " + Integer.toString(time) + "ms");
  }


  /*
   * Recursively sorts an array of items. If the array
   * is larger than specified in params - truncates the array.
   * Also increments the number of inversions in MergeSort class.
   * @param arr Array to sort.
   * @param from From which element sorting is needed.
   * @param to Till which element sorting is needed.
   * @return Array of sorted elements.
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
