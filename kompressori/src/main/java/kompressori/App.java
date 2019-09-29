package kompressori;

import kompressori.data_structures.*;
import java.util.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println("Input string: ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        Huffman h = new Huffman();
        LZW lzw = new LZW();

        int[] lempelEncoded = lzw.encode(input);
        HuffmanWrapper encodedInput = h.encode(input);

        System.out.println("\n" + "...................." + "\n");
        System.out.println("Lempel-Ziv-Welch: ");
        System.out.printf("Encoded: %s\n", Arrays.toString(lempelEncoded));
        System.out.printf("Decoded: %s\n", lzw.decode(lempelEncoded));
        System.out.println("\n" + "...................." + "\n");
        System.out.println("Huffman: ");
        System.out.printf("Encoded: %s\n", encodedInput.getEncodedString());
        System.out.printf("Decoded: %s\n", h.decode(encodedInput));
        System.out.println("\n" + "...................." + "\n");
    }
}
