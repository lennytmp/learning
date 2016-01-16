package euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Problem22 {
  public static void main(String[] args) {
    Long sum = new Long(0);
    String[] names = readNames();
    Arrays.sort(names);
    for (int i = 0; i < names.length; i++) {
      int index = i + 1;
      int value = 0;
      for (char c : names[i].toCharArray()) {
        int charVal = Character.getNumericValue(c);
        if (charVal > 0) {
          value += Character.getNumericValue(c) - 9;
        }
      }
      int score = index * value;
      if (names[i].equals("\"COLIN\"")) {
        System.out.println(names[i] + " - " + score);
      }
      sum += score;
    }
    System.out.println(sum);
  }


  public static String[] readNames() {
    String[] result = new String[0];
    try {
      BufferedReader br = new BufferedReader(
        new FileReader("/Users/levvy/dev/learning/euler/p022_names.txt")
      );
      try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String everything = sb.toString();
        result = everything.split(",");
      } finally {
        br.close();
      }
    } catch (IOException e) {
        e.printStackTrace();
    } 
    return result; 
  }
}
