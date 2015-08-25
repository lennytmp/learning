/**
 * Performs Merge Sort algorithm,
 * Calculates the number of inversions,
 * Supports file input or auto generated array.
 * Run instructions: java MergeSort path/to/file.txt
 */

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

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
      arr = MergeSort.readFromFile(args[0]);
    }
    if (arr.length == 0) {
      arr = MergeSort.generateArray(5000000, 5000000);
    }
    long t = System.nanoTime();
    if (MergeSort.debug) {
      System.out.print("Input: ");
      MergeSort.printArr(arr);
    }

    int[] arr2 = MergeSort.sort(arr, 0, arr.length);
    if (MergeSort.debug) {
      System.out.print("Result: ");
      MergeSort.printArr(arr2);
    }
    int time = Math.round((System.nanoTime() - t)/1000000);
    System.out.println("Number of inversions " + Long.toString(inversions));
    System.out.println("Time elapsed " + Integer.toString(time) + "ms");
  }


  /*
   * Reads input from file, handles the errors.
   * @param filePath Path to the input file.
   * @return Array of integers, one integer from each line.
   *  if an error occured, empty array is returned.
   */
  public static int[] readFromFile(String filePath) {
    System.out.println("Trying to read file "+ filePath);
    int[] result = new int[]{};
    // TODO (@lenny) Refactor this to make Exceptions handling nicer.
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      return result;
    }
    try {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
      while (line != null) {
          sb.append(line);
          sb.append(',');
          line = br.readLine();
      }
      String[] lines = sb.toString().split(",");
      System.out.println(Integer.toString(lines.length) + " lines read");
      result = new int[lines.length];
      for (int i = 0; i < lines.length; i++) {
        result[i] = Integer.parseInt(lines[i]);
      }
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        System.out.println("Error while reading file");
      }
      return result;
    }
  }


  /*
   * Generates a random array of integers.
   * @param size Size of the array.
   * @param maxValue Maximum allowed value, not inclusive.
   * @return Array of randomly generated integers.
   */
  public static int[] generateArray(int size, int maxValue) {
    int[] result = new int[size];
    Random generator = new Random();
    for (int k = 0; k < size; k++) {
      result[k] = generator.nextInt(maxValue);
    }
    return result;
  }


  /*
   * Recursively sorts an array of items. If the array
   * is larger than specified in params - truncates the array.
   * Also increments the number of inversions in MergeSort class.
   * @param arr Array to sort.
   * @param startIndex From which element sorting is needed.
   * @param endIndex Till which element sorting is needed.
   * @return Array of sorted elements.
   */
  public static int[] sort(int[] arr, int startIndex, int endIndex) {
    if ((endIndex - startIndex) > 1) {
      int l = startIndex + Math.round((endIndex - startIndex) / 2);
      int[] left = MergeSort.sort(arr, startIndex, l);
      int[] right = MergeSort.sort(arr, l, endIndex);
      int i = 0, j = 0;
      int[] result = new int[endIndex - startIndex];
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
    return new int[]{arr[startIndex]};
  }

  /*
   * Prints the array in a nice readable manner.
   * @param arr Array to print
   */
  public static void printArr(int[] arr) {
    System.out.print('[');
    for (int x : arr) {
      System.out.print(Integer.toString(x) + ' ');
    }
    System.out.println(']');
  }
}
