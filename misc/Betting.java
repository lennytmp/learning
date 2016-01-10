package misc;

import java.util.Random;

class Betting {
  static int balance;
  static int savings;
  static Random rnd = new Random();

  public static void main(String[] args) {
    int totalSavings = 0;
    int initialBalance = 100;
    for (int j = 0; j < 30000; j++) {
      int i = 0;
      balance = initialBalance;
      savings = 0;
      while (balance > 0) {
        int num = rnd.nextInt(1001);
        int bet = Integer.max(1, initialBalance - balance);
        if (bet > balance) {
          savings += balance;
          balance = 0;
          break;
        }
        if (num <= 495) {
          balance += bet;
        } else {
          balance -= bet;
        }
        if (balance == 110) {
          balance -= 10;
          savings += 10;
        }
        i++;
      }
      totalSavings += savings;
    }
    System.out.println("3m invested, " + totalSavings + " saved.");
  }
}
