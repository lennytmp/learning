/**
 * Algorithm to find max sub array in an array.
 */

package misc;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class MaxSubArray {


  /**
   * Sets two arrays and calls function to find
   * max sub arrays in them.
   * @param args Program arguments. Not used.
   */
  public static void main(String[] args) {
    int[] arr = {-18, -12, 2, -1, 8, -15, 6};
    System.out.println(Arrays.toString(arr));
    processArray(arr);

    int[] arr2 = {-18, -12, -2, -1, -8, -15, -6};
    System.out.println(Arrays.toString(arr2));
    processArray(arr2);
  }


  /**
   * Finds the maximum subarray and prints it.
   * @param arr Array to process.
   */
  static void processArray(int[] arr) {
    ArrayList<Integer> positiveIndexes = getPositiveIndexes(arr);
    if (positiveIndexes.size() == 0) {
      printMaxElement(arr);
      return;
    }
    HashMap<Integer, Integer> maxPerIndex = getMaxPerIndex(arr, positiveIndexes);
    int maxIndex = getMaxIndex(maxPerIndex);
    printMaxSubArray(arr, maxIndex, maxPerIndex.get(maxIndex));
  }


  /**
   * Get all indexes with positive values as an ArrayList.
   * @param arr The array to search in.
   * @return ArrayList of elements' indexes with positive values. 
   */
  static ArrayList<Integer> getPositiveIndexes(int[] arr) {
    ArrayList<Integer> positiveIndexes = new ArrayList<Integer>(); 
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        positiveIndexes.add(i);
      }
    }
    return positiveIndexes;
  }

  /**
   * Finds the maximum element and prints it as an array.
   * @param arr The array to search in.
   */
  static void printMaxElement(int[] arr) {
    int max = arr[0];
    System.out.print('[');
    for (int elem : arr) {
      if (elem > max) {
        max = elem;
      }
    }
    System.out.print(max);
    System.out.println(']');
  }


  /**
   * Calculates for each given index, what is the maximum sum when
   * iterating forward.
   * @param arr The array to search in.
   * @param indexes The indexes of the elements to start search from.
   * @return Max sums keyed by indexes.
   */
  static HashMap<Integer, Integer> getMaxPerIndex(int[] arr, 
                                                  ArrayList<Integer> indexes) {
    HashMap<Integer, Integer> maxPerIndex = new HashMap<Integer, Integer>();
    for (int index : indexes) {
      int sum = 0;
      maxPerIndex.put(index, sum);
      for (int i = index; i < arr.length; i++) {
         sum += arr[i];
         if (sum > maxPerIndex.get(index)) {
           maxPerIndex.put(index, sum);
         }
      }
    }
    return maxPerIndex;
  }


  /**
   * Gets the maximum element's index in a given HashMap. 
   * @param hashmap The indexes of the elements to start search from.
   * @return Index of the maximum element.
   */
  static int getMaxIndex(HashMap<Integer, Integer> hashmap) {
    Iterator it = hashmap.entrySet().iterator();
    int maxIndex = -1;
    while (it.hasNext()) {
      HashMap.Entry pair = (HashMap.Entry)it.next();
      if (maxIndex == -1 || (Integer)pair.getValue() > hashmap.get(maxIndex)) {
        maxIndex = (Integer)pair.getKey();
      }
    }
    return maxIndex;
  }


  /**
   * Prints the sub array starting from the give index, 
   * with sum of all elements equal to sum.
   * @param arr The source array.
   * @param index The index to start from.
   * @param sum The sum of all elements in a subarray.
   */
  static void printMaxSubArray(int[] arr, int index, int sum) {
    int curSum = 0;
    System.out.print("[");
    for (int i = index; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
      curSum += arr[i];
      if (curSum == sum) {
        System.out.println("]");
        return;
      }
    }
  }
}
