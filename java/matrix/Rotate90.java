package matrix;


class Rotate90 {
  private static final int LENGTH = 3;

  public static void main(String[] args) {
    int[][] matrix = new int[LENGTH][LENGTH];
    int v = 1;
    for (int i = 0; i < LENGTH; i++) {
      for (int j = 0; j < LENGTH; j++) {
        matrix[i][j] = v; 
        v++;
      }
    }
    MatrixMap.print(matrix);
    System.out.println();
    MatrixMap.print(rotate(matrix));
  }


  public static int[][] rotate(int[][] matrix) {
    for (int i = 0; i < (matrix.length - (matrix.length % 2)) / 2; i++) {
      for (int j = 0; j < matrix.length - i * 2 - 1; j++) {
        int top = matrix[i + j][i];
        int right = matrix[matrix.length - i - 1][i + j];
        int bottom = matrix[matrix.length - j - i - 1][matrix.length - i - 1];
        int left = matrix[i][matrix.length - j - i - 1];
        matrix[i + j][i] = left;
        matrix[matrix.length - i - 1][i + j] = top;
        matrix[matrix.length - j - i - 1][matrix.length - i - 1] = right;
        matrix[i][matrix.length - j - i - 1] = bottom;
      }
    }
    return matrix;
  }
}
