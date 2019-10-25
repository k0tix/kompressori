package kompressori.domain;

import org.junit.Before;
import org.junit.Test;

public class CompressorTest {
    private Compressor compressor;

    @Before
    public void setUp() {
        this.compressor = new Compressor();
    }

    @Test
    public void returnsCorrectErrorMessage() {
        this.compressor.decode("Not valid path", "lzw");
        this.compressor.encode("Not valid path", "huffman");
    }
}