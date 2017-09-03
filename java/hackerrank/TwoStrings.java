package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/two-strings
public class TwoStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tests; i++) {
            String str = sc.nextLine();
            HashSet<Character> letters = new HashSet<Character>();
            for (char letter : str.toCharArray()) {
                letters.add(letter);
            }
            boolean found = false;
            str = sc.nextLine();
            for (char letter : str.toCharArray()) {
                if (letters.contains(letter)) {
                    System.out.println("YES");
                    found = true;
                    break;
                };
            }
            if (!found) {
                System.out.println("NO");
            }
        }
    }
}
