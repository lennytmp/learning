/**
 * Implements algorithms to get 1 from any number with a set of operations.
 */

package dp;

import java.util.ArrayList;
import java.util.HashMap;


class TopDown implements Runnable {
  public void run() {
    int a = 135897;
    System.out.println("Steps to break " + a + ": " + getMinSteps(a));
  }


  public static int getMinSteps(int a) {
    ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
    queue.add(new QueueItem(a, 0, Integer.toString(a)));
    int i = 0;
    while (queue.size() > 0) {
      QueueItem item = queue.remove(0);
      i++;
      if (item.value == 1) {
        System.out.println(item.calcs);
        System.out.println(i + " cycles for top down");
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
    }
    return -1;
  }
}


class BottomUp implements Runnable {
  public static HashMap<Integer, QueueItem> ready = new HashMap<Integer, QueueItem>();

  public void run() {
    System.out.println("I am a bottom up");
    int a = 135897;
    System.out.println("Steps to break " + a + ": " + getMinSteps(a));
  }


  public static int getMinSteps(int finish) {
    ArrayList<QueueItem> queue = new ArrayList<QueueItem>();
    queue.add(new QueueItem(1, 0, "1"));
    int i = 0;
    while (queue.size() > 0) {
      QueueItem item = queue.remove(0);
      if (item.value > finish) {
        continue;
      }
      i++;
      if (item.value == finish) {
        System.out.println(item.calcs);
        System.out.println(i + " cycles for bottom up");
        return item.curStep;
      }
      QueueItem newElem;
      if (ready.get(item.value * 3) == null) {
        newElem = new QueueItem(
          item.value * 3,
          item.curStep + 1,
          item.calcs + " * 3");
        queue.add(newElem);
        ready.put(newElem.value, newElem);
      }
      if (ready.get(item.value * 2) == null) {
        newElem = new QueueItem(
          item.value * 2,
          item.curStep + 1,
          item.calcs + " * 2");
        queue.add(newElem);
        ready.put(newElem.value, newElem);
      }
      if (ready.get(item.value + 1) == null) {
        newElem = new QueueItem(
          item.value + 1,
          item.curStep + 1,
          '(' + item.calcs + " + 1)");
        queue.add(newElem);
        ready.put(newElem.value, newElem);
      }
    }
    return -1;
  }
}

class MinStepsTo1Comb {
  /**
   * Sets the number and calls the algorithm.
   * @param args Program arguments, unused.
   */
  public static void main(String[] args) {
    Thread t = new Thread(new TopDown(), "Top down");
    Thread b = new Thread(new BottomUp(), "Top down");
    b.start();
    t.start();
  }
  

  }
