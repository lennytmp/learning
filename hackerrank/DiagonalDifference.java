package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/diagonal-difference
public class DiagonalDifference {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int num = sc.nextInt();
                if (i == j) {
                    diag1 += num;
                }
                if (i + j == length - 1) {
                    diag2 += num;
                }
            }   
        }
        System.out.println(Math.abs(diag2 - diag1));
    }
}
