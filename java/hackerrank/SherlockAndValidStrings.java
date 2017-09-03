package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/sherlock-and-valid-string
public class SherlockAndValidStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        HashMap<Character, Integer> charFreq = getCharFreq(s);
        if (charFreq.size() == 1) { // only one character
            System.out.println("YES");
            return;
        }
        HashMap<Integer, Integer> freqFreq = getFreqFreq(charFreq);
        if (freqFreq.size() > 2) { //too many letters to correct
            System.out.println("NO");
            return;
        }
        if (freqFreq.size() == 1) { //everything already fine
            System.out.println("YES");
            return;
        }
        if (checkFreqFreqMap(freqFreq)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
        return;
    }
    
    private static boolean checkFreqFreqMap(HashMap<Integer, Integer> map) {
        int[] keys = new int[2];
        int[] values = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1 && entry.getValue() == 1) {
                return true;
            }
            keys[i] = entry.getKey();
            values[i] = entry.getValue();
            i++;
        }
        //Sorting by values
        if (values[0] > values[1]) {
            int tmp = values[1];
            values[1] = values[0];
            values[0] = tmp; 
            tmp = keys[1];
            keys[1] = keys[0];
            keys[0] = tmp; 
        }
        if ((values[0] == 1) && (keys[0] - keys[1] == 1)) {
            return true;
        } else {
            return false;
        }
    }
    
    private static HashMap<Integer, Integer> getFreqFreq(HashMap<Character, Integer> map) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (int n : map.values()) {
            if (result.get(n) == null) {
                result.put(n, 1);
            } else {
                result.put(n, result.get(n) + 1);
            }
        }
        return result;
    }
    
        
    private static HashMap<Character, Integer> getCharFreq(String s) {
        HashMap<Character, Integer> result = new HashMap<Character, Integer>();
        for (char l : s.toCharArray()) {
            if (result.get(l) == null) {
                result.put(l, 1);
            } else {
                result.put(l, result.get(l) + 1);
            }
        }
        return result;
    }
}
