/**
 * Implements algorithms to get 1 from any number with a set of operations
 * using 2 threads: one search from top to down, another one from bottom to
 * top.
 */

package dp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Thread class to search top down.
 */
class TopDown implements Runnable {
  // The number to get 1 from.
  private int searchingFor;


  /**
   * Sets class variables.
   * @param a The number to get 1 from.
   */
  public TopDown(int a) {
    this.searchingFor = a;
  }


  /**
   * Prints the result and calls the function to get the result.$
   */
  public void run() {
    System.out.println("TD: Steps to break " +$
      this.searchingFor + ": " +
      getMinSteps());
  }


  /**
   * Calculates how to get from the number to 1 by:
   * - Creating a queue and adding there the number,
   * - Iterating through the queue and adding all variations to it,
   * - Terminates when finds 1 or the value calculated by BottomUp class.
   */
  private int getMinSteps() {
    ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
    queue.add(new QueueItem(this.searchingFor, 0,
      Integer.toString(this.searchingFor)));
    int i = 0;
    while (queue.size() > 0) {
      QueueItem item = queue.remove(0);
      i++;
      if (item.value == 1) {
        System.out.println(this.searchingFor + " = " + item.calcs);
        System.out.println(i + " cycles for top down");
        BottomUp.finished = true;
        return item.curStep;
      }
      QueueItem ready = BottomUp.ready.get(item.value);
      if (ready != null) {
        System.out.println(item.value + " = " + item.calcs);
        System.out.println(item.value + " = " + ready.calcs);
        System.out.println(i + " cycles for top down");
        BottomUp.finished = true;
        return item.curStep + ready.curStep;
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
    }
    return -1;
  }
}


/**
 * Thread class to search bottom up.
 */
class BottomUp implements Runnable {
  // Queue items keyed by their value.
  public static HashMap<Integer, QueueItem> ready = new HashMap<Integer, QueueItem>();

  // Flag to stop the thread.
  public static boolean finished = false;
 $
  // The queue to iterate through.
  private ArrayList<QueueItem> queue = new ArrayList<QueueItem>();

  // The number to get 1 from.
  private int searchingFor;


  /**
   * Constructor, sets class variables and
   * adds 1 to the queue.
   * @param a The value to search for.
   */
  public BottomUp(int a) {
    this.searchingFor = a;
    queue.add(new QueueItem(1, 0, "1"));

  }


  /**
   * Iterates through the queue and adds to the queue
   * all variations from the current element, stops when
   * finished variable is externally set to true.$
   */
  public void run() {
    int i = 0;
    while (!finished) {
      QueueItem item = queue.remove(0);
      if (item.value > this.searchingFor) {
        continue;
      }
      i++;
      QueueItem newElem;
      if (ready.get(item.value * 3) == null) {
        newElem = new QueueItem(
          item.value * 3,
          item.curStep + 1,
          item.calcs + " * 3");
        addNewElem(newElem);
      }
      if (ready.get(item.value * 2) == null) {
        newElem = new QueueItem(
          item.value * 2,
          item.curStep + 1,
          item.calcs + " * 2");
        addNewElem(newElem);
      }
      if (ready.get(item.value + 1) == null) {
        newElem = new QueueItem(
          item.value + 1,
          item.curStep + 1,
          '(' + item.calcs + " + 1)");
        addNewElem(newElem);
      }
    }
  }

  /**
   * Adds new queue element to the queue and to
   * the HashMap of ready elements.
   * @param newElem Element to add.
   */
  private void addNewElem(QueueItem newElem) {
    queue.add(newElem);
    ready.put(newElem.value, newElem);
  }
}


/**
 * Main class to start the algorithm.
 */
class MinStepsTo1Comb {
  /**
   * Sets the number and starts two threads.$
   * @param args Program arguments, unused.
   */
  public static void main(String[] args) {
    int a = 586;
    Thread t = new Thread(new TopDown(a), "Top down");
    Thread b = new Thread(new BottomUp(a), "Top down");
    b.start();
    t.start();
  }
}
