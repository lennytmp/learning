package hackerrank;

import java.util.Scanner;


// https://www.hackerrank.com/challenges/lonely-integer
public class LonelyInteger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] values = new int[100];
        for (int i = 0; i < n; i++) {
            values[sc.nextInt()]++;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 1) {
                System.out.println(i);
                break;
            }
        }
    }
}
