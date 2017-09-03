package euler;

class Problem12 {
  static class maxTriangle {
    long n = 1;
    long triangle = 1;
  }
  
  static maxTriangle maxTr = new maxTriangle();

  public static void main(String[] args) {
    long n = 1;
    long factors = 0;
    while (factors < 500) {
      n = n + 1;
      factors = getFactorsNum(getTriangle(n));
    }
    System.out.println(getTriangle(n) + " " + factors);
  }

  private static long getTriangle(long n) {
    long result = maxTr.triangle;
    for (long i = maxTr.n + 1; i <= n; i++) {
      result += i;
    }
    if (maxTr.n < n) {
      maxTr.n = n;
      maxTr.triangle = result;
    }
    return result;
  }


  private static long getFactorsNum(long n) {
    long result = 0;
    for (long i = 1; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        result += 2;
      }
    }
    return result;
  }
}
