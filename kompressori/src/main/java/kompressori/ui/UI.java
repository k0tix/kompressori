package kompressori.ui;

import java.util.Scanner;

import kompressori.domain.Compressor;
import kompressori.io.FileHandler;
import kompressori.utils.Timer;

public class UI {
    private Scanner reader;
    private Compressor compressor;
    private Timer timer;
    private FileHandler handler;
    
    public UI(Scanner reader) {
        this.reader = reader;
        this.compressor = new Compressor();
        this.timer = new Timer();
        this.handler = new FileHandler();
    }

    public void start() {
        System.out.println("Welcome to kompressori");
        System.out.println();
        menu();
    }


    /**
     * Run encoding/decoding based on commanline arguments
     * @param args
     */
    public void arguments(String[] args) {
        // TODO
    }

    /**
     * Main ui menu
     */
    public void menu() {
        while (true) {
            System.out.println("Encode or decode or compare?");
            System.out.println("1 - Encode");
            System.out.println("2 - Decode");
            System.out.println("3 - Compare");
            System.out.println("Any other input halts the process");
            System.out.print(": ");

            String input = this.reader.nextLine();
            String filepath = "";

            switch (input) {
                case "1":
                    encode();
                    break;
                case "2":
                    decode();
                    break;
                case "3":
                    compare();
                    break;
                default:
                    System.exit(1);
                    break;
            }
        }
    }

    /**
     * Encoding menu
     */
    public void encode() {
        String algorithm = algorithm();
        String filepath = ask("What is the filepath to the file you want to encode?");

        this.compressor.encode(filepath, algorithm);
    }

    /**
     * Decoding menu
     */
    public void decode() {
        String filepath = ask("What file do you want to decode?");
        String savepath = ask("Where do you want to save the decoded file?");
        this.compressor.decode(filepath, savepath);
    }

    /**
     * Algorithm menu
     * @return
     */
    public String algorithm() {
        System.out.println("Which algorithm would you like to use?");
        System.out.println("1 -- Huffman");
        System.out.println("2 -- LZW");
        System.out.print(": ");
        String input = this.reader.nextLine();
        if (input.equals("1")) {
            return "huffman";
        } else if (input.equals("2")) {
            return "lzw";
        }

        return "";
    }

    /**
     * Comparison screen
     */
    public void compare() {
        System.out.println("Comparison");
        System.out.println("Manual (1) data or file input (2)? ");
        System.out.print(": ");
        String inputType = this.reader.nextLine();
        Compressor compress = new Compressor();

        byte[] data = "Test data if no input".getBytes();
        if (inputType.equals("1")) {
            System.out.println("Write input data here: ");
            data = this.reader.nextLine().getBytes();
        } else {
            String filepath = ask("What is the filepath of the input file?");
            data = this.handler.readFile(filepath);
        }

        System.out.println("Initial size: " + data.length + " bytes");

        // LZW encode
        timer.start();
        byte[] lempelEncoded = compress.lzwEncode(data);
        timer.stop();
        long lzwEncodeTime = timer.getTime();

        timer.reset();

        // LZW decode
        timer.start();
        byte[] lzwDecoded = compress.lzwDecode(lempelEncoded);
        timer.stop();
        long lzwDecodeTime = timer.getTime();

        timer.reset();

        // Huffman encode
        timer.start();
        byte[] huffmanEncoded = this.compressor.huffmanEncode(data);
        timer.stop();
        long huffmanEncodeTime = timer.getTime();

        // Huffman decode
        timer.start();
        byte[] huffmanDecoded = this.compressor.huffmanDecode(huffmanEncoded);
        timer.stop();
        long huffmanDecodeTime = timer.getTime();

        timer.reset();

        System.out.println("\n" + "...................." + "\n");
        System.out.println("Lempel-Ziv-Welch: ");
        System.out.printf("Size: %d bytes\n", lempelEncoded.length);
        System.out.printf("Ratio: %.2f %%\n", (double) lempelEncoded.length / data.length * 100);
        System.out.printf("Encode time: %d ms\n", lzwEncodeTime);
        System.out.printf("Decode time: %d ms\n", lzwDecodeTime);
        System.out.println("\n" + "...................." + "\n");
        System.out.println("Huffman: ");
        System.out.printf("Size: %d bytes\n", huffmanEncoded.length);
        System.out.printf("Ratio: %.2f %%\n", (double) huffmanEncoded.length / data.length * 100);
        System.out.printf("Encode time: %d ms\n", huffmanEncodeTime);
        System.out.printf("Decode time: %d ms\n", huffmanDecodeTime);
        System.out.println("\n" + "...................." + "\n");
    }

    public String ask(String question) {
        System.out.println(question);
        System.out.print(": ");
        return this.reader.nextLine();
    }
}