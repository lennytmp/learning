/**
 * Implementation of combinations for array of integers,
 * e.g. for [1,2] get [[1], [2], [1,2]]
 */

package misc;

import java.util.ArrayList;
import java.util.HashSet;

public class Combination {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3};
    System.out.println(getAllVars(arr));
  }

  public static HashSet<HashSet<Integer>> getAllVars(int[] items) {
    HashSet<HashSet<Integer>> result = new HashSet<HashSet<Integer>>();
    for (int i : items) {
      HashSet<Integer> tmp = new HashSet<Integer>();
      tmp.add(i);
      result.add(tmp);
    }
    int k = 0;
    boolean f = true;
    while (f) {
      HashSet<HashSet<Integer>> resultBef = new HashSet<HashSet<Integer>>(result);
      for (HashSet<Integer> cur : resultBef) {
        for (int it : items) {
          if (cur.contains(it)) {
            continue;
          }
          HashSet<Integer> tmp = new HashSet<Integer>(cur);
          tmp.add(it);
          result.add(tmp);
          if (tmp.size() == items.length) {
            f = false;
            break;
          }
        }
        if (!f) {
          break;
        }
      }
    }
    return result;
  }
}
