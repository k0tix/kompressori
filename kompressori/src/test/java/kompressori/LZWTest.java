package kompressori;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * Unit test for Huffman encoding
 */
public class LZWTest {

    private LZW lzw;

    @Before
    public void setUp() {
        this.lzw = new LZW();
    }

    @Test
    /**
     * Check that no data is lost while encoding
     */
    public void noDataLoss() {
        String input = "This is just a test string";
        int[] encodedInput = this.lzw.encode(input);
        assertEquals(input, this.lzw.decode(encodedInput));
    }

    @Test
    public void correctEncodingIntegerList() {
        String input = "abcd";
        int[] encodedInput = this.lzw.encode(input);
        assertArrayEquals(new int[]{97, 98, 99, 100}, encodedInput);
    }
}
