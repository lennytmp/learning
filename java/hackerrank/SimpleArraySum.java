package hackerrank;

import java.io.*;
import java.util.*;

// https://www.hackerrank.com/challenges/simple-array-sum
public class SimpleArraySum {

    public static void main(String[] args) {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        try {
            cin.readLine();
            String stringArr = cin.readLine();
            String[] arr = stringArr.split(" ");
            int sum = 0;
            for (String s : arr) {
                sum += Integer.parseInt(s);
            }
            System.out.println(sum);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
