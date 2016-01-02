/**
 * Solves consumer - producer problem.
 */

package threads;

import java.util.ArrayList;
import java.util.Random;


/**
 * Buffer class with an ArrayList of values.
 */
class Buffer {
  public static ArrayList<Integer> value = new ArrayList<Integer>();
}


/**
 * Abscrtact class for common methods.
 */
abstract class MyThread {
  // Rondomised seed to produce random values.
  public static Random rnd = new Random();


  /**
   * Puts the thread to sleep for up to 5 seconds
   * and handles exceptions.
   */
  public void trySleepRnd() {
    try {
      Thread.sleep(rnd.nextInt(5000));
    } catch(InterruptedException e) {};
  }
  

  /**
   * Sets the state of thread to wait for the object
   * and handles exceptions.
   * @param obj The object to wait for.
   */
  public void tryWait(Object obj) {
    try {
      obj.wait();
    } catch(InterruptedException e) {};
  }
}

/**
 * Producer thread class.
 */
class Producer extends MyThread implements Runnable {
  /**
  * Tries to produce a value to the buffer, if
  * buffer is full - waits. 
  */
  public void run() {
    while(true) {
      synchronized(Buffer.value) {
        if (Buffer.value.size() < 3) {
          Buffer.value.add(rnd.nextInt(100));
          System.out.println("Value produced: " + Buffer.value);
          Buffer.value.notifyAll();
        } else {
          tryWait(Buffer.value);
        }
      }
      trySleepRnd(); // to make it more fun
    }
  }
}


/**
 * Consumer thread class.
 */
class Consumer extends MyThread implements Runnable {
  /**
  * Tries to consume a value from the buffer, if
  * buffer is empty - waits. 
  */
  public void run() {
    while(true) {
      synchronized(Buffer.value) {
        if (Buffer.value.size() > 0) {
          int top = Buffer.value.remove(0);
          System.out.println("Value consumed " + top);
          Buffer.value.notifyAll();
        } else {
          tryWait(Buffer.value);
        }
      }
      trySleepRnd(); // to make it more fun
    }
  }
}


class ProdCons {
  /**
   * Spawns two threads and starts them.
   * @param args Program arguments, not used.
   */
  public static void main(String[] args) {
    System.out.println(Buffer.value);
    Thread prod = new Thread(new Producer(), "Producer");
    Thread cons = new Thread(new Consumer(), "Consumer");
    prod.start();
    cons.start();
  }
}
