package hackerrank;

import java.io.*;
import java.util.*;
import java.math.BigInteger;


// https://www.hackerrank.com/challenges/a-very-big-sum
public class AVeryBigSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        String[] arr = sc.nextLine().split(" ");
        BigInteger result = new BigInteger("0");
        for (String s : arr) {
            result = result.add(new BigInteger(s));
        }
        System.out.println(result);
    }
}
