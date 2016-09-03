import java.util.Scanner;
import java.util.Arrays;

// https://www.hackerrank.com/contests/master/challenges/sudoku
public class Sudoku {
    static Scanner sc = new Scanner(System.in);
    static final boolean DEBUG = false;
    
    public static void main(String[] args) {
        int casesNum = sc.nextInt();
        for (int caseNum = 0; caseNum < casesNum; caseNum++) {
            int[][] board = readBoard();
            solve(board);
            printBoard(board);
        }
    }
    
    private static void solve(int[][] board) {
        HistoryNode current = new HistoryNode();
        boolean[][][] constraints = current.getConstraints(board, new boolean[9][9][9]);
        while (true) {
            HistoryNode next = current.getNextStep(board, constraints);
            if (next == null) {
                if (current.parent == null) {
                    throw new IllegalStateException("Unsolvable, tried everything");
                }
                constraints = 
                  current.parent.failChildGetConstraints(current, board, constraints);
                current = current.parent;
                if (DEBUG) {
                    System.out.println("Dead end, going back");
                }
            } else {
                constraints = next.applyStepGetConstraints(board, constraints);
                if (DEBUG) {
                    System.out.println(Arrays.toString(next.coord) + ": " + (next.value + 1));
                }
                if (isFinished(board)) {
                    return;
                }
                current = next;
            }
        }
    }
    
    private static boolean isFinished(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                if (j != 9 - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    private static int[][] readBoard() {
        int[][] result = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = sc.nextInt();
            }
        }
        return result;
    }
}

class HistoryNode {
    int[] coord;
    int value;
    HistoryNode parent = null;
    boolean[][][] failedChildrenConstraints = new boolean[9][9][9];
    
    HistoryNode getNextStep(int[][] board, boolean[][][] restricted) {
        int[] coord = getMostRestrictedCell(board, restricted);
        if (coord.length == 0) {
            return null;
        }
        HistoryNode result = new HistoryNode();
        result.value = getFirstAllowedEl(restricted[coord[0]][coord[1]]);
        if (result.value == -1) {
            return null;
        }
        result.coord = coord;
        result.parent = this;
        return result;
    }
    
    boolean[][][] failChildGetConstraints(HistoryNode child,
                                          int[][] board,
                                          boolean[][][] restricted) {
        failedChildrenConstraints[child.coord[0]][child.coord[1]][child.value] = true;
        board[child.coord[0]][child.coord[1]] = 0;
        return getConstraints(board, failedChildrenConstraints);
    }
    
    boolean[][][] applyStepGetConstraints(int[][] board, boolean[][][] restricted) {
        board[coord[0]][coord[1]] = value + 1;
        return getConstraints(board, failedChildrenConstraints);
    }
    
    private static int[] getMostRestrictedCell(int[][] board, boolean[][][] constraints) {
        int[] result = new int[2];
        int minAllowed = 10;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    continue;
                }
                int allowed = 0;
                for (int k = 0; k < 9; k++) {
                    if (!constraints[i][j][k]) {
                        allowed++;
                    }
                }
                if (allowed < minAllowed) {
                    minAllowed = allowed;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        if (minAllowed == 10) {
            result = new int[0];
        }
        return result;
    }
    
    private static int getSquare(int row, int col) {
        return (int)Math.floor(row/3)*3 + col / 3;
    }
    
    public static boolean[][][] getConstraints(int[][] board, boolean[][][] restrictedConst) {
        boolean[][][] constraints = new boolean[9][9][9];
        boolean[][] usedPerSquare = new boolean[9][9],
                    usedPerCol = new boolean[9][9],
                    usedPerRow = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int square = getSquare(i, j);
                if (board[i][j] != 0) {
                    int usedValue = board[i][j] - 1;
                    usedPerSquare[square][usedValue] = true;
                    usedPerCol[j][usedValue] = true;
                    usedPerRow[i][usedValue] = true;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    continue;
                }
                int square = getSquare(i, j);
                for (int k = 0; k < 9; k++) {
                    if (usedPerSquare[square][k] 
                        || usedPerCol[j][k] 
                        || usedPerRow[i][k]
                        || restrictedConst[i][j][k]) {
                        constraints[i][j][k] = true;
                    }
                }
            }
        }
        return constraints;
    }
    
    private static int getFirstAllowedEl(boolean[] restricted) {
        for (int i = 0; i < restricted.length; i++) {
            if (!restricted[i]) {
                return i;
            }
        }
        return -1;
    }
}

