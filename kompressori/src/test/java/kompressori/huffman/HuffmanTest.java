package kompressori.huffman;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
        byte[] input = "This is some test data that is not very long but still very useful".getBytes();

        int[] frequencies = this.huffman.getFrequencies(input);
        HuffmanNode tree = this.huffman.createHuffmanTree(frequencies);
        byte[] encodedInput = this.huffman.encode(input);
        
        assertArrayEquals(input, this.huffman.decode(encodedInput, tree));
    }

    @Test
    public void correctEncodingCodes() {
        byte[] input = "aaaaabababssh".getBytes();
        byte[] encodedInput = this.huffman.encode(input);
    }

    /**
     * Calculates correct character frequencies
     */
    @Test
    public void calculateCorrectFrequencies() {
        int[] frequencies = this.huffman.getFrequencies("aaabbc".getBytes());
        assertEquals(3, frequencies[0xff & (byte) 'a']);
        assertEquals(2, frequencies[0xff & (byte) 'b']);
        assertEquals(1, frequencies[0xff & (byte) 'c']);
    }

    /**
     * Test Huffman tree building
     */
    @Test
    public void buildCorrectHuffmanTree() {
        byte[] input = "TTTTaa ".getBytes();

        HuffmanNode T = new HuffmanNode((byte) 'T', 4, null, null);
        HuffmanNode a = new HuffmanNode((byte) 'a', 2, null, null);
        HuffmanNode space = new HuffmanNode((byte) ' ', 1, null, null);

        HuffmanNode node1 = new HuffmanNode((byte) 0, 3, a, space);
        HuffmanNode root = new HuffmanNode((byte) 0, 7, T, node1);

        int[] frequencies = this.huffman.getFrequencies(input);
        HuffmanNode buildTree = this.huffman.createHuffmanTree(frequencies);
        
        assertEquals(root.getLeft().getFrequency(), buildTree.getLeft().getFrequency());
        assertEquals(root.getLeft().getCharacter(), buildTree.getLeft().getCharacter());

        assertEquals(root.getRight().getFrequency(), buildTree.getRight().getFrequency());
        assertEquals(root.getRight().getCharacter(), buildTree.getRight().getCharacter());

        assertEquals(root.getRight().getLeft().getFrequency(), buildTree.getRight().getLeft().getFrequency());
        assertEquals(root.getRight().getRight().getFrequency(), buildTree.getRight().getRight().getFrequency());
        assertTrue(buildTree.getLeft().isLeafNode());
        assertTrue(buildTree.getRight().getRight().isLeafNode());
    }
}
