package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/bigger-is-greater
public class BiggerIsGreater {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tests; i++) {
            StringBuffer input = new StringBuffer(sc.nextLine());
            if (input.length() == 1) {
                System.out.println("no answer");
                continue;
            }
            int pos = getChangePos(input);
            if (pos == -1) {
                System.out.println("no answer");
                continue;
            }
            StringBuffer result = new StringBuffer();
            for (int j = 0; j <= pos; j++) {
                result.append(input.charAt(j));
            }
            StringBuffer rightPart = sort(input.substring(pos + 1));
            result.append(rightPart);
            char tmp = result.charAt(pos);
            int newPos = pos + findBigger(rightPart, tmp) + 1;
            result.setCharAt(pos, result.charAt(newPos));
            result.setCharAt(newPos, tmp);
            System.out.println(result);
        }
    }
    
    
    private static int findBigger(StringBuffer s, char c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > c) {
                return i;
            }
        }
        return -1;
    }
    
    private static StringBuffer sort(String input) {
        char[] arr = input.toCharArray();
        Arrays.sort(arr);
        StringBuffer result = new StringBuffer();
        for (char c : arr) {
            result.append(c);
        }
        return result;
    }
    
    private static int getChangePos(StringBuffer input) {
        for (int j = input.length() - 2; j >= 0; j--) {
            if (input.charAt(j) < input.charAt(j + 1)) {
                return j;
            }
        }
        return -1;
    }
}
