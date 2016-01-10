package misc;

import java.util.Random;

class Betting {
  static int balance;
  static int savings;
  static Random rnd = new Random();

  public static void main(String[] args) {
    int totalSavings = 0;
    int initialBalance = 100;
    int wins = 0;
    for (int j = 0; j < 100000; j++) {
      balance = initialBalance;
      for (int k = 0; k < 2; k++) {
        int num = rnd.nextInt(1001);
        int bet = balance;
        if (num <= 495) {
          balance += bet;
        } else {
          balance -= bet;
        }
        if (balance > initialBalance) {
          wins++;
          break;
        }
      }
    }
    System.out.println("100k attempts, " + wins + " wins.");
  }
}
