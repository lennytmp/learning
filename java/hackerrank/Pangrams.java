package hackerrank;

import java.io.*;
import java.util.*;

// https://www.hackerrank.com/challenges/pangrams
public class Pangrams {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        HashSet<Character> letters = new HashSet<Character>();
        for (char letter : line.toCharArray()) {
            letter = Character.toLowerCase(letter);
            if (letter >= 'a' && letter <= 'z') {
                letters.add(letter);
            }
        }
        if (letters.size() == 26) {
            System.out.println("pangram");
        } else {
            System.out.println("not pangram");
        }
    }
}
