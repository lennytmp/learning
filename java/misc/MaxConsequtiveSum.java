package misc;

class MaxConsequtiveSum {
  public static void main(String[] args) {
    int[] arr = {2, -8, 3, -2, 4, -10};
    System.out.println(findMaxSumN2(arr));
    System.out.println(findMaxSumN(arr));
  }

  static int findMaxSumN(int[] arr) {
    Integer maxSum = null, consSum = null;
    for (int element : arr) {
      if (maxSum == null) {
        maxSum = element;
        consSum = element;
        continue;
      }
      if (element > consSum + element) {
        consSum = element;
      } else {
        consSum += element;
      }
      if (consSum > maxSum) {
        maxSum = consSum;
      }
    }
    if (maxSum == null) {
      maxSum = 0;
    }
    return maxSum;
  }


  static int findMaxSumN2(int[] arr) {
    if (arr.length == 0) {
      return 0;
    }
    Integer[] maxs = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = i; j < arr.length; j++) {
        sum += arr[j];
        if (maxs[i] == null || maxs[i] < sum) {
          maxs[i] = sum;
        }
      }
    }
    int result = maxs[0];
    for (int max : maxs) {
      if (max > result) {
        result = max;
      }
    }
    return result;
  }
}
