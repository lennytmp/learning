/**
 * Algorithm to check if a string has correct brackets.
 */

package misc;

import linkedlist.LinkedList;
import java.util.Arrays;

class BracketsChecker {

  // Open brackets symbols.
  static String OPEN = "{[(";

  // Closed brackets symbols in same order.
  static String CLOSED = "}])";
  
  /**
   * Checks the algorithm for a set of strings.
   * @param args Arguments of the program. Unused.
   */
  public static void main(String[] args) {
    String[] codes = {"asdasd([asdasad])[", "({[]})", "({[}])"};
    for (String code : codes) {
      System.out.println(code + " : " + isValid(code));
    }
  }


  /**
   * Checks if the code is valid by adding all items to the stack.
   * @param code String to check.
   * @return True - if the code valid, False - if it's not.
   */
  public static boolean isValid(String code) {
    LinkedList<Integer> s = new LinkedList<Integer>();
    for (char c : code.toCharArray()) {
      int bracketType = OPEN.indexOf(c);
      if (bracketType != -1) {
        s.push(bracketType);
      }
      bracketType = CLOSED.indexOf(c);
      if (bracketType != -1) {
        if (s.value != bracketType) {
          return false;
        }
        s.pop();
      }
    }
    return s.size == 0;
  }
}
