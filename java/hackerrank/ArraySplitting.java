package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.math.BigInteger;

// https://www.hackerrank.com/challenges/array-splitting
public class ArraySplitting {
    static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
    static class QueueElem {
        int from, to;
        BigInteger sum;
        int score = 0;
        
        QueueElem(int from, int to, BigInteger sum) {
            this.from = from;
            this.to = to;
            this.sum = sum;
        }
        
        QueueElem(int from, int to, BigInteger sum, int score) {
            this.from = from;
            this.to = to;
            this.sum = sum;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        EfficientReader er = new EfficientReader((int)Math.pow(2, 14)*10);
        int casesNum = er.nextInt();
        for (int caseNum = 0; caseNum < casesNum; caseNum++) {
            int arrSize = er.nextInt();
            BigInteger sum = BigInteger.ZERO;
            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; i++) {
                arr[i] = er.nextInt();
                sum = sum.add(new BigInteger(Integer.toString(arr[i])));
            }
            Queue<QueueElem> queue = new LinkedList<>(); 
            queue.add(new QueueElem(0, arrSize, sum));
            int maxScore = 0;
            while (queue.size() > 0) {
                QueueElem el = queue.poll();
                if (el.score > maxScore) {
                    maxScore = el.score;
                }
                if (!el.sum.mod(TWO).equals(BigInteger.ZERO)) {
                    continue;
                }
                sum = BigInteger.ZERO;
                for (int i = el.from; i < el.to - 1; i++) {
                    sum = sum.add(new BigInteger(Integer.toString(arr[i])));
                    BigInteger newSum = el.sum.divide(TWO);
                    if (sum.equals(newSum)) {
                        queue.add(new QueueElem(el.from, i + 1, newSum, el.score + 1));
                        queue.add(new QueueElem(i + 1, el.to, newSum, el.score + 1));
                        break;
                    } else if (sum.compareTo(newSum) == 1) {
                        break;
                    }
                }
            }
            System.out.println(maxScore);
        }
    }
}

