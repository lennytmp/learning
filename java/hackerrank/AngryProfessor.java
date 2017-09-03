package hackerrank;

import java.util.Scanner;


// https://www.hackerrank.com/challenges/angry-professor
public class AngryProfessor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            String[] nk = sc.nextLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            String[] arrivals = sc.nextLine().split(" ");
            int present = 0, absent = 0;
            boolean decided = false;
            for (String arrival : arrivals) {
                if (Integer.parseInt(arrival) <= 0) {
                    present++;
                } else {
                    absent++;
                    if (n - absent < k) {
                        System.out.println("YES");
                        decided = true;
                        break;
                    }
                }
            }
            if (!decided) {
                System.out.println((present < k) ? "YES" : "NO");
            }
        }
    }
}
