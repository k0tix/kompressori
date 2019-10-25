package kompressori.domain;

import java.nio.ByteBuffer;
import kompressori.huffman.Huffman;
import kompressori.io.FileHandler;
import kompressori.lzw.LZW;

/**
 * Class that combines compress algorithm operation logic
 */
public class Compressor {
    private FileHandler file;
    private Huffman huffman;
    private LZW lzw;

    public Compressor() {
        this.file = new FileHandler();
        this.huffman = new Huffman();
        this.lzw = new LZW();
    }
    
    /**
     * Encodes a file with the given algorithm
     * @param filepath
     * @param algorithm
     */
    public void encode(String filepath, String algorithm) {
        if (this.file.isValidPath(filepath)) {
            return;
        }
        
        byte[] input = this.file.readFile(filepath);
        
        if (algorithm.equals("huffman")) {
            byte[] data = this.huffmanEncode(input);
            this.file.writeFile(filepath + ".huffman", data);
        } else if (algorithm.equals("lzw")) {
            byte[] data = this.lzw.encode(input);
            this.file.writeFile(filepath + ".lzw", data);
        }  
    }

    /**
     * Decodes a file using the algorithm based on the file extension
     * @param filepath
     */
    public void decode(String filepath, String savepath) {
        byte[] data = this.file.readFile(filepath);

        if (filepath.contains("huffman")) {
            this.file.writeFile(savepath, this.huffmanDecode(data));
        } else if (filepath.contains("lzw")) {
            this.file.writeFile(savepath, this.lzw.decode(data));
        } else {
            System.out.println("Not a valid file for decoding. Must end with .huffman or .lzw");
        }
    }

    /**
     * Encodes byte to input with Huffman encoding to byte array that can be stored in a file
     * @param input
     * @return
     */
    public byte[] huffmanEncode(byte[] input) {
        int[] freq = this.huffman.getFrequencies(input);
        byte[] encodedInput = this.huffman.encode(input, freq);

        ByteBuffer buffer = ByteBuffer.allocate((freq.length + 1 << 2) + encodedInput.length);
        buffer.putInt(freq.length);
        for (int i = 0; i < freq.length; i++) {
            buffer.putInt(freq[i]);
        }
        buffer.put(encodedInput);

        return buffer.array();
    }

    /**
     * Decodes Huffman encoded byte input from a stored file
     * @param input
     * @return
     */
    public byte[] huffmanDecode(byte[] input) {
        ByteBuffer buffer = ByteBuffer.wrap(input);
        int freqSize = buffer.getInt();
        int[] freq = new int[freqSize];

        for (int i = 0; i < freqSize; i++) {
            freq[i] = buffer.getInt();
        }

        byte[] encodedInput = new byte[buffer.remaining()];
        for (int i = 0; i < encodedInput.length; i++) {
            encodedInput[i] = buffer.get();
        }
        
        return this.huffman.decode(encodedInput, this.huffman.createHuffmanTree(freq));
    }

    public byte[] lzwEncode(byte[] input) {
        return this.lzw.encode(input);
    }

    public byte[] lzwDecode(byte[] encodedInput) {
        return this.lzw.decode(encodedInput);
    }
}