package hackerrank;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


// https://www.hackerrank.com/challenges/cipher
public class Cipher {

    public static void main(String[] args) throws IOException {
        EfficientReader er = new EfficientReader((int)Math.pow(10, 6) + 6*2);
        int n = er.nextInt();
        int k = er.nextInt();
        char[] input = er.next().toCharArray();
        char[] output = new char[n];
        int koef = 0;
        for (int i = 0; i < n; i++) {
            int inputBit = Character.getNumericValue(input[i]);
            int outputBit = inputBit^koef;
            output[i] = Character.forDigit(outputBit, 10);
            koef = koef^outputBit;
            if (i - k + 1 >= 0) {
                koef = koef^Character.getNumericValue(output[i - k + 1]);
            }
            System.out.print(output[i]);
        }
    }
    
}

class EfficientReader {
    private BufferedReader br;
    
    public EfficientReader(int bufferSize) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in), bufferSize);
    }
    
    public String next() throws IOException {
        int c = (int)' ';
        StringBuilder result = new StringBuilder();
        while (" \r\n\t".indexOf((char)c) != -1) {
            c = br.read();
            if (c == -1) {
                return null;
            }
        }
        while (c != -1 && " \r\n\t".indexOf((char)c) == -1) {
            result.append((char)c);
            c = br.read();
        }
        return result.toString();
    }
    
    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
