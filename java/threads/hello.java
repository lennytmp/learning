/**
 * Implements a very simple app using multiple threads.
 */
package threads;


class Hello implements Runnable {
  // Id of the thread
  int id;

  /**
   * Constructor of the thread, inits object properties.
   * @param id Id of the thread.
   */
  public Hello(int id) {
    this.id = id;
  }


  /**
   * Thread main code: prints 4 time the id and
   * handles exceptions.
   */
  public void run() {
    try {
      for (int i = 0; i < 4; i++) {
        System.out.println(this.id);
        Thread.sleep(50);
      }
    } catch (InterruptedException e) {
      System.out.println("Thread " + this.id + " interrupted.");
      return;
    }
  }

  
  /**
   * Spawns two threads and starts them.
   * @param args Program arguments, not used.
   */
  public static void main(String[] args) {
    Thread t1 = new Thread(new Hello(1), "Thread 1");
    Thread t2 = new Thread(new Hello(2), "Thread 2");
    t1.start();
    t2.start();
  }
}
