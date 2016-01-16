package euler;

import java.util.Arrays;

class Problem19 {
  static int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static void main(String[] args) {
    //day, month, year, weekday (1 - mon)
    int[] day = {1,1,1901, 2};
    int sundays = 0;
    boolean leap = false;
    while (day[0] != 3 || day[1] != 12 || day[2] != 2000) {
      if (day[3] == 7 && day[0] == 1) {
        sundays++;
      }
      day[0]++;
      day[3]++;
      if (day[3] == 8) {
        day[3] = 1;
      }
      if (day[0] > daysPerMonth[day[1]-1]) {
        if (leap && day[1] == 2 && day[0] == 29) {
          continue;
        }
        day[0] = 1;
        day[1]++;
        if (day[1] > 12) {
          day[1] = 1;
          day[2]++;
          leap = ((day[2] % 100 != 0) && (day[2] % 4 == 0)) ||
                 (day[2] % 400 == 0);
        }
      }
    }
    System.out.println(sundays);
  }
}
