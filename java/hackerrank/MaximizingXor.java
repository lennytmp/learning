package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;


// https://www.hackerrank.com/challenges/maximizing-xor
public class MaximizingXor {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt(), r = sc.nextInt();
        int maxXor = 0;
        for (int i = l; i <= r; i++) {
            for (int j = i; j <= r; j++) {
                int xor = i^j;
                if (xor > maxXor) {
                    maxXor = xor;
                }
            }
        }
        System.out.println(maxXor);
    }
}
