package hackerrank;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;


// https://www.hackerrank.com/challenges/matrix-rotation-algo
public class AlgoMatrixRotation {
    private Scanner sc = new Scanner(System.in);
    private int M, N, R;
    private long[][] input;
    private Map<Integer, Point[]> clockwisePoints = new HashMap<>();
    
    private static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        public String toString() {
            return "(" + this.x + ", " + this.y + ')';
        }
       
        @Override
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return this.y == p.y && this.x == p.x;
        }
    }
    
    public static void main(String[] args) {
        AlgoMatrixRotation s = new AlgoMatrixRotation();
        s.M = s.sc.nextInt();
        s.N = s.sc.nextInt();
        s.R = s.sc.nextInt();
        s.input = s.readMatrix();
        long[][] result = new long[s.M][s.N];
        result = s.rotateMatrix();
        AlgoMatrixRotation.writeMatrix(result); 
    }
    
    private long[][] rotateMatrix() {
        long[][] result = new long[this.M][this.N];
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                Point curLoc = new Point(i, j);
                Point newLoc = this.getNewLocation(curLoc);
                result[newLoc.y][newLoc.x] = this.input[i][j];
            }
        }
        return result;
    }
    
    private Point getNewLocation(Point cur) {
        int ringNum = Math.min(Math.min(cur.y, cur.x),
                               Math.min(this.M - cur.y - 1, this.N - cur.x - 1));
        int ringPerimeter = (this.M - ringNum * 2)*2 + (this.N - ringNum * 2)*2 - 4;
        Point[] ringPoints = generateAllPoints(ringNum, ringPerimeter);
        int localR = this.R % ringPerimeter;
        if (localR == 0) {
            return cur;
        }
        int index = Arrays.asList(ringPoints).indexOf(cur);
        return ringPoints[(index + localR) % ringPerimeter];
    }
    
    private Point[] generateAllPoints(int ringNum, int ringPerimeter) {
        Point[] result = clockwisePoints.get(ringNum);
        if (result != null) {
            return result;
        }
        result = new Point[ringPerimeter];
        int minX = ringNum, maxX = N - ringNum - 1;
        int minY = ringNum, maxY = M - ringNum - 1;
        int n = 0;
        int x, y;
        x = minX;
        for (y = minY; y <= maxY; y++) {
            result[n] = new Point(y, x);
            n++;
        }
        y = maxY;
        for (x = minX + 1; x <= maxX; x++) {
            result[n] = new Point(y, x);
            n++;
        }
        x = maxX;
        for (y = maxY - 1; y >= minY; y--) {
            result[n] = new Point(y, x);
            n++;
        }
        y = minY;
        for (x = maxX - 1; x > minX; x--) {
            result[n] = new Point(y, x);
            n++;
        }
        clockwisePoints.put(ringNum, result);
        return result;
    }
    
    private long[][] readMatrix() {
        long[][] arr = new long[this.M][this.N];
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                arr[i][j] = this.sc.nextLong();
            }
        }
        return arr;
    }
    
    private static long[][] writeMatrix(long[][] arr) {
        int M = arr.length;
        int N = arr[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j]);
                if (j != N - 1) {
                    System.out.print(' ');
                }
            }
            if (i != M - 1) {
                System.out.println();
            }
        }
        return arr;
    }
}
