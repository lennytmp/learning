package euler;

import java.util.HashMap;

class Problem14 {
  private static class ChainLength {
    long value;
    int chainLength;

    public ChainLength(long value, int chainLength) {
      this.value = value;
      this.chainLength = chainLength;
    }
  }

  static HashMap<Long, ChainLength> ready = new HashMap<Long, ChainLength>();

  static int getChainLength(long n) {
    long i = n;
    int counter = 1;
    while (i > 1) {
      if (ready.get(i) != null) {
        counter += ready.get(i).chainLength - 1;
        break;
      }
      if (i % 2 == 0) {
        i = i / 2;
      } else {
        i = 3*i + 1;
      }
      counter++;
    }
    ready.put(n, new ChainLength(n, counter));
    return counter;
  }


  public static void main(String[] args) {
    int maxValue = 1;
    long maxI = 1;
    for (long i = 1; i < 1000000; i++) {
      int tmp = getChainLength(i);
      if (maxValue < tmp) {
        maxValue = tmp;
        maxI = i;
      }
    }
    System.out.println(maxI + " " + maxValue);
  }
}
