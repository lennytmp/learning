/**
 * Implements algorithms to get 1 from any number with a set of operations.
 */

package dp;

import java.util.ArrayList;

class MinStepsTo1 {
  /**
   * Class for each item in the queue.
   */
  private static class QueueItem {
    // Current value in an item.
    int value;

    // Number of steps to get to this value.
    int curStep;

    // How did we get here in a string.
    String calcs;

    /**
     * Constructor of an item, sets class properties.
     * @param value Value of the item.
     * @param curStep How many steps did it take to get to this value.
     * @param calcs String representing how did we get here exactly.
     */
    QueueItem(int value, int curStep, String calcs) {
      this.value = value;
      this.curStep = curStep;
      this.calcs = calcs;
    }
  }


  /**
   * Sets the number and calls the algorithm.
   * @param args Program arguments, unused.
   */
  public static void main(String[] args) {
    int a = 92;
    System.out.println("Steps to break " + a + ": " + getMinStepsTopDown(a));
    System.out.println("Steps to break " + a + ": " + getMinStepsBottomUp(a));
  }


  /**
   * Recursion wrapper for the top down approach:
   * creates a queue and calls the recursive function.
   * @param a Value to transfrom to one. 
   * @return Number of steps to get to 1.
   */
  public static int getMinStepsBottomUp(int a) {
    ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
    queue.add(new QueueItem(1, 0, "1"));
    return getMinStepsBottomUp(queue, a);
  }


  /**
   * Recursive function to calculate minimum steps using
   * top down approach.
   * @param queue The queue of elements to analyze.
   * @return Number of steps to get to 1.
   */
  private static int getMinStepsBottomUp(ArrayList<QueueItem> queue, int finish) {
    QueueItem item = queue.remove(0);
    if (item.value == finish) {
      System.out.println(item.calcs);
      return item.curStep;
    }
    queue.add(new QueueItem(
        item.value * 3,
        item.curStep + 1,
        item.calcs + " * 3"
    ));
    queue.add(new QueueItem(
        item.value * 2,
        item.curStep + 1,
        item.calcs + " * 2"
    ));
    queue.add(new QueueItem(
          item.value + 1,
          item.curStep + 1,
          '(' + item.calcs + " + 1)"
    ));
    return getMinStepsBottomUp(queue, finish);
  }


  /**
   * Recursion wrapper for the top down approach:
   * creates a queue and calls the recursive function.
   * @param a Value to transfrom to one. 
   * @return Number of steps to get to 1.
   */
  public static int getMinStepsTopDown(int a) {
    ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
    queue.add(new QueueItem(a, 0, Integer.toString(a)));
    return getMinStepsTopDown(queue);
  }


  /**
   * Recursive function to calculate minimum steps using
   * top down approach.
   * @param queue The queue of elements to analyze.
   * @return Number of steps to get to 1.
   */
  private static int getMinStepsTopDown(ArrayList<QueueItem> queue) {
    QueueItem item = queue.remove(0);
    if (item.value == 1) {
      System.out.println(item.calcs);
      return item.curStep;
    }
    if (item.value % 3 == 0) {
      queue.add(new QueueItem(
          item.value / 3,
          item.curStep + 1,
          item.calcs + " / 3"
      ));
    }
    if (item.value % 2 == 0) {
      queue.add(new QueueItem(
          item.value / 2,
          item.curStep + 1,
          item.calcs + " / 2"
      ));
    }
    queue.add(new QueueItem(
          item.value - 1,
          item.curStep + 1,
          '(' + item.calcs + " - 1)"
    ));
    return getMinStepsTopDown(queue);
  }
}

