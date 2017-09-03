package hackerrank;

import java.util.Scanner;
import java.util.Arrays;


// https://www.hackerrank.com/challenges/maxsubarray
public class TheMaximumSubarray {
    private Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        TheMaximumSubarray s = new TheMaximumSubarray();
        s.doAnalysis();
    }
    
    public void doAnalysis() {
        int numArrays = sc.nextInt();
        for (int i = 0; i < numArrays; i++) {
            int numElements = sc.nextInt();
            int[] els = new int[numElements];
            Integer maxCSum = null, maxNCSum = null;
            for (int j = 0; j < numElements; j++) {
                int el = sc.nextInt();
                els[j] = el;
                maxCSum = getMaxCSum(maxCSum, els, j);
            }
            Arrays.sort(els);
            System.out.println(maxCSum + " " + getMaxNCSum(els));
        }
    }
    
    private int getMaxNCSum(int[] sortedEls) {
        Integer sum = null;
        for (int i = sortedEls.length - 1; i >= 0; i--) {
            if (sum == null || sum + sortedEls[i] > sum) {
                sum = (sum == null) ? sortedEls[i] : sum + sortedEls[i];
            }
        }
        return sum;
    }
    
    private int getMaxCSum(Integer maxCSum, int[] els, int lastIndex) {
        Integer sum = null;
        for (int i = lastIndex; i >= 0; i--) {
            if (sum == null) {
                sum = els[i];
            } else {
                sum += els[i];
            }
            if (maxCSum == null || sum > maxCSum) {
                maxCSum = sum;
            }
            if (i != lastIndex && sum <= 0) {
                break;
            }
        }
        return maxCSum;
    }
}
