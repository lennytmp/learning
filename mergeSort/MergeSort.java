import java.util.Arrays;
import java.util.Random;


public class MergeSort {
  static boolean debug = false;


  public static void main(String[] args) {
    long t = System.nanoTime();
    int[] arr = MergeSort.generateArray(5000000, 5000000);
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
    System.out.println("Time elapsed " + Integer.toString(time) + "ms");
  }


  public static int[] generateArray(int size, int maxValue) {
    int[] result = new int[size];
    Random generator = new Random();
    for (int k = 0; k < size; k++) {
      result[k] = generator.nextInt(maxValue);
    }
    return result;
  }


  public static int[] sort(int[] arr, int startIndex, int endIndex) {
    if ((endIndex - startIndex) > 1) {
      int l = startIndex + Math.round((endIndex - startIndex) / 2);
      int[] left = MergeSort.sort(arr, startIndex, l);
      int[] right = MergeSort.sort(arr, l, endIndex);
      int i = 0, j = 0;
      int[] result = new int[endIndex - startIndex];
      for (int k = 0; k < result.length; k++) {
        if (i < left.length && (j >= right.length || left[i] < right[j])) {
          result[k] = left[i];
          i++;
        } else {
          result[k] = right[j];
          j++;
        }
      }
      return result;
    }
    return new int[]{arr[startIndex]};
  }


  public static void printArr(int[] arr) {
    System.out.print('[');
    for (int x : arr) {
      System.out.print(Integer.toString(x) + ' ');
    }
    System.out.println(']');
  }
}
