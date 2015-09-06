/**
 * Abstract class for sorting algorithms. It has:
 * The number of inversions variable, debug variable;
 * Main function to get arguments;
 * Function to generate an array;
 * Function to read an array from file;
 * Function to print an array; 
 * Run instructions: java sorting/Sort path/to/file.txt
 */


package sorting;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public abstract class Sort {
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
      arr = readFromFile(args[0]);
    }
    if (arr.length == 0) {
      arr = generateArray(5000000, 5000000);
    }
    long t = System.nanoTime();
    if (MergeSort.debug) {
      System.out.print("Input: ");
      printArr(arr);
    }

    int[] arr2 = MergeSort.sort(arr, 0, arr.length);
    if (MergeSort.debug) {
      System.out.print("Result: ");
      printArr(arr2);
    }
    int time = Math.round((System.nanoTime() - t)/1000000);
    System.out.println("Number of inversions " + Long.toString(inversions));
    System.out.println("Time elapsed " + Integer.toString(time) + "ms");
  }


  /*
   * Mock up function to sort the array 
   * @param arr Array to sort.
   * @param from From which element sorting is needed.
   * @param to Till which element sorting is needed.
   * @return Array of sorted elements.
   */
  public static int[] sort(int[] arr, int from, int to) {
    return arr;
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
