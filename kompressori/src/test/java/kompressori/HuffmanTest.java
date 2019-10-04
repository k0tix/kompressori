package kompressori;

import kompressori.data_structures.*;
import java.util.Map;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * Unit test for Huffman encoding
 */
public class HuffmanTest {

    private Huffman huffman;

    @Before
    public void setUp() {
        this.huffman = new Huffman();
    }

    @Test
    /**
     * Check that no data is lost while encoding
     */
    public void noDataLoss() {
        String input = "aaaaabbbbdd";
        HuffmanWrapper encodedInput = this.huffman.encode(input);
        assertNotEquals(input, encodedInput.getEncodedString());
        assertEquals(input, this.huffman.decode(encodedInput));
    }

    @Test
    public void correctEncodingCodes() {
        String input = "aaaaabababssh";
        HuffmanWrapper encodedInput = this.huffman.encode(input);
        assertEquals("0000010010010110110111", encodedInput.getEncodedString());
    }

    /**
     * Calculates correct character frequencies
     */
    @Test
    public void calculateCorrectFrequencies() {
        Map<Character, Integer> frequencies = this.huffman.frequencyMap("aaabbc");
        assertEquals(3, frequencies.get('a').intValue());
        assertEquals(2, frequencies.get('b').intValue());
        assertEquals(1, frequencies.get('c').intValue());
    }
}
