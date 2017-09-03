package hackerrank;

import java.io.*;
import java.util.*;
import java.math.BigInteger;


// https://www.hackerrank.com/challenges/flipping-bits
public class FlippingBits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            BigInteger n = new BigInteger(sc.nextLine());
            StringBuffer result = new StringBuffer();
            String bits = n.toString(2);
            char[] arr = n.toString(2).toCharArray();
            for (int j = 0; j < 32 - bits.length(); j++) {
                result.append("1");
            }
            for (char s : arr) {
                if (s == '1') {
                    result.append('0');
                } else {
                    result.append('1');
                }
            }
            n = new BigInteger(result.toString(), 2);
            System.out.println(n);
        }
    }
}
