package hackerrank;

import java.util.Scanner;
import java.math.BigInteger;


// https://www.hackerrank.com/challenges/counter-game
public class CounterGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            BigInteger num = sc.nextBigInteger();
            System.out.println(getWinner(num));
        }
    }
    
    private static String getWinner(BigInteger num) {
        int endZeros = 0, ones = 0;
        for (int i = num.bitLength() - 1; i >= 0; i--) {
            if (num.testBit(i)) {
                ones++;
                endZeros = 0;
            } else {
                endZeros++;
            }
        }
        String winner = ((endZeros + ones) % 2 == 1) ? "Richard" : "Louise";
        return winner;
    }
}
