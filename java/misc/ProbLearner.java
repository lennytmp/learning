import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class ProbLearner {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ProbLearner model = new ProbLearner(200, 255);
    while (true) {
      System.out.println("Enter your message");
      String message = sc.next();
      int[] input = stringToIntArr(message); 
      int[] output = model.getOutput(input);
      System.out.println(intArrToString(output));
      System.out.println("Did you like the response?");
      if (sc.next().equals("y")) {
        model.learn(input, output, true);
      } else {
        System.out.println("Please inter the expected behaviour or '-'");
        String rightAnswer = sc.next();
        if (!rightAnswer.equals("-")) {
          model.learn(input, output, stringToIntArr(rightAnswer));
        } else {
          model.learn(input, output, false);
        }
      }
    }
  }

  private static int[] stringToIntArr(String s) {
    char[] seq = s.toCharArray();
    int[] result = new int[seq.length];
    for (int i = 0; i < seq.length; i++) {
      result[i] = (int)seq[i];
    }
    System.out.println(s + " transformed to " + Arrays.toString(result));
    return result;
  }

  private static String intArrToString(int[] arr) {
    char[] seq = new char[arr.length];
    for (int i = 0; i < seq.length; i++) {
      seq[i] = (char)arr[i];
    }
    return new String(seq);
  }

  /* Class really starts. Everything above should be moved */

  int outputSize, outputLimit;
  List<Event> memory = new LinkedList<>();
  Random rnd = new Random();

  public ProbLearner(int outputSize, int outputLimit) {
    this.outputSize = outputSize;
    this.outputLimit = outputLimit;
  }

  public int[] getOutput(int[] input) {
    int[] result = new int[outputSize];
    for (int i = 0; i < outputSize; i++) {
      result[i] = getBestGuess(input, i);
    }
    return result;
  }

  public void learn(int[] input, int[] output, boolean correct) {
    Event e = new Event();
    e.input = input;
    e.output = output;
    e.correct = correct;
    memory.add(e);
  }
  
  public void learn(int[] input, int[] output, int[] correct) {
    Event e = new Event();
    e.input = input;
    e.output = output;
    e.correct = false;
    e.goodOutput = correct;
    memory.add(e);
  }

  private int getBestGuess(int[] input, int index) {
    double[] probabilityTable = new double[outputLimit];
    for (Event e : memory) {
      int sameInputs = 0;
      for (int i = 0; i < e.input.length; i++) {
        if (input.length > i && input[i] == e.input[i]) {
          sameInputs++;
        }
      }
      double similarity = (double)sameInputs / input.length;
      int correctNum, incorrectNum;
      if (e.goodOutput.length > index) {
        probabilityTable[e.goodOutput[index]] += similarity;
      }
      if (e.output.length > index) {
        if (e.correct) {
          probabilityTable[e.output[index]] += similarity;
        } else {
          probabilityTable[e.output[index]] -= similarity;
        }
      }
    }
    return getRandByTable(probabilityTable);
  }

  private int getRandByTable(double[] probabilityTable) {
    double sum = 0;
    for (double el : probabilityTable) {
      if (el > 0) {
        sum += el;
      }
    }
    if (sum == 0) {
      return rnd.nextInt(outputLimit);
    }
    int guess = rnd.nextInt((int)(sum*1000));
    for (int i = 0; i < probabilityTable.length; i++) {
      guess -= (int)(probabilityTable[i]*1000);
      if (guess < 0) {
        return i;
      }
    }
    return 0;
  }
}

class Event {
  int[] input;
  int[] output;
  boolean correct;
  int[] goodOutput;
}
