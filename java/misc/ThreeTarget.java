package misc;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ThreeTarget {
  public static void main(String[] args) {
    int[] arr = {1, 1, 3, 2, 2};
    int target = 5;
    System.out.println(getSubarraysNaively(arr, target));
    System.out.println(getSubarrays(arr, target));
  }

  public static List<List<Integer>> getSubarrays(int[] arr, int target) {
    List<List<Integer>> result = new LinkedList<>(); 
    Map<Integer, Integer> valuesToMaxIndex = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      valuesToMaxIndex.put(arr[i], i);
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        int remainder = target - arr[i] - arr[j];
        Integer maxIndex = valuesToMaxIndex.get(remainder);
        boolean match = false;
        if (maxIndex != null && maxIndex > j) {
          result.add(getListOfThree(arr[i], arr[j], remainder));
        }
      }
    }
    return result;
  }

  public static List<List<Integer>> getSubarraysNaively(int[] arr, int target) {
    List<List<Integer>> result = new LinkedList<>();
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        for (int k = j + 1; k < arr.length; k++) {
          if (arr[i] + arr[j] + arr[k] == target) {
            result.add(getListOfThree(arr[i], arr[j], arr[k]));
          }
        }
      }
    }
    return result;
  }

  private static List<Integer> getListOfThree(int a, int b, int c) {
    LinkedList<Integer> triple = new LinkedList<>();
    triple.add(a);
    triple.add(b);
    triple.add(c);
    return triple;
  }
}
