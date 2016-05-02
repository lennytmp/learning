package hackerrank;

import java.io.*;
import java.util.*;


// https://www.hackerrank.com/challenges/utopian-tree
public class UtopianTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            int cycles = sc.nextInt();
            int height = 1;
            if (cycles == 0) {
                System.out.println(height);
                continue;
            }
            for (int cycle = 0; cycle < cycles; cycle++) {
                if (cycle % 2 == 0) {
                    height *= 2;
                } else {
                    height += 1;
                }
            }
            System.out.println(height);
        }
    }
}
