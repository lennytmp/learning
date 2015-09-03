import java.util.Arrays;
import java.util.Random;


public class QuickSort {
  static long inversions = 0;
  
  public static void main(String[] args) {
    int[] arr = {3, 4, 8, 7, 9, 1, 2, 6, 5, 10};
    System.out.println(Arrays.toString(arr));
    arr = sort(arr, 0, arr.length);
    System.out.println(Arrays.toString(arr));
    System.out.println("Finished in " + Long.toString(inversions) + " steps.");
  }


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
