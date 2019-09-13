package kompressori;

import kompressori.domain.Huffman;
import kompressori.domain.HuffmanWrapper;
import java.util.*;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        System.out.println("Input string: ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        Huffman h = new Huffman();
        HuffmanWrapper encodedInput = h.encode(input);

        System.out.println("Encoded string:");
        System.out.println(encodedInput.getEncodedString());

        System.out.println("");
        System.out.println("Decoded string:");
        System.out.println(h.decode(encodedInput));
    }
}
