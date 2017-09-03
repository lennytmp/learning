import java.util.Scanner;

class NumberOfBits {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int input = sc.nextInt();
    int num = input;
    int bits = 1;
    System.out.println("Bit representation: " + Integer.toBinaryString(input));
    while (num >= 2 || num <= -2) {
      bits++;
      num = num >> 1;
      System.out.println("::" + num);
      System.out.println("::" + Integer.toBinaryString(num));
    }
    System.out.println("Num bits: " + bits);
    System.out.println("Real bit count: " + Integer.toBinaryString(input).length());
  }
}
