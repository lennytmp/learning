package misc;

import java.util.Scanner;

class FuzzySearch {
  private static String haystack;
  private static String needle;
  private static int dist;
  private static int count;


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter haystack");
    haystack = sc.next();
    System.out.println("Enter needle");
    needle = sc.next();
    System.out.println("Enter dist");
    dist = sc.nextInt();
    System.out.println("Searching...");
    System.out.println(isFuzzyMatch() + " in " + count);
  }


  private static boolean isFuzzyMatch() {
    for (int i = 0; i <= haystack.length() - needle.length() + dist; i++) {
      if (isMatching(i, 0, 0)) {
        System.out.println("Matching from " + i);
        return true;
      }
    }
    return false;
  }

  private static boolean isMatching(
      int hIndex,
      int nIndex,
      int changes) {
    count++;
    if (changes > dist) {
      return false;
    }
    if (nIndex == needle.length()) {
      return true;
    }
    if (hIndex >= haystack.length()) {
      return isMatching(hIndex, nIndex + 1, changes + 1);
    }
    if (needle.charAt(nIndex) == haystack.charAt(hIndex)) {
      return isMatching(hIndex + 1, nIndex + 1, changes);
    } 
    return isMatching(hIndex + 1, nIndex, changes + 1) ||
      isMatching(hIndex, nIndex + 1, changes + 1);
    
  }
}
