package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


// https://www.hackerrank.com/challenges/cavity-map
public class CavityMap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String grid[] = new String[n];
        for(int grid_i=0; grid_i < n; grid_i++){
            grid[grid_i] = in.next();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isBorder(i, j, n) || !isHigh(grid, i, j)) {
                    System.out.print(grid[i].charAt(j));
                } else {
                    System.out.print('X');
                }
            }
            System.out.println();
        }
    }
    
    private static boolean isHigh(String grid[], int i, int j) {
        char c = grid[i].charAt(j);
        return c > (int)grid[i - 1].charAt(j) &&
            c > (int)grid[i + 1].charAt(j) &&
            c > (int)grid[i].charAt(j-1) &&
            c > (int)grid[i].charAt(j+1);
    }
    
    private static boolean isBorder(int i, int j, int n) {
        return i == 0 || j == 0 || i == (n - 1) || j == (n - 1);
    }
}

