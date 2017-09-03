package euler;

class Problem17 {
  static String[] oneToTen = {
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  static String[] tenToTwenty = {
    "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", 
    "seventeen", "eighteen", "nineteen"
  };
  static String[] tenths = {
    "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
  };
  static String[] tenPow = {"hundred", "thousand"};


  public static void main(String[] args) {
    long result = 0;
    for (int i = 1; i <= 1000; i++) {
      result += numToStr(i).length();
    }
    System.out.println(result);
  }

  public static String numToStr(int num) {
    String result = "";
    int thousands = num / 1000;
    if (thousands > 0) {
      result += oneToTen[thousands - 1];
      result += "thousand";
    }
    num = num % 1000;
    int hundreds = num / 100;
    if (hundreds > 0) {
      result += oneToTen[hundreds - 1];
      result += "hundred";
      if (num % 100 > 0) { 
        result += "and";
      }
    }
    num = num % 100;
    int tens = num / 10;
    if (tens == 1) {
      result += tenToTwenty[num - 10];
      return result;
    }
    if (tens > 1) {
      result += tenths[tens- 1];
    }
    num = num % 10;
    if (num > 0) {
      result += oneToTen[num - 1];
    }
    return result; 
  }
}
