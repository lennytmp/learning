package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.math.BigInteger;

// https://www.hackerrank.com/challenges/stockmax
public class StockMax {
    public static void main(String[] args) throws IOException {
        EfficientReader er = new EfficientReader((int)Math.pow(10, 6)*3);
        int casesNum = er.nextInt();
        for (int caseNum = 0; caseNum < casesNum; caseNum++) {
            int arrSize = er.nextInt();
            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; i++) {
                arr[i] = er.nextInt();
            }
            int[] sell = new int[arrSize];
            int max = arr[arrSize - 1];
            for (int i = arrSize - 1; i >= 0; i--) {
              sell[i] = (max < arr[i]) ? 0 : max;
              if (arr[i] > max) {
                max = arr[i];
              }
            }
            System.out.println(Arrays.toString(sell));
            BigInteger score = BigInteger.ZERO;
            for (int i = 0; i < arrSize; i++) {
              if (sell[i] != 0) {
                score = score.add(new BigInteger(Integer.toString(sell[i] - arr[i])));
              }
            }
            System.out.println(score);
        }
    }
}

