package kompressori.huffman;

/**
 * Class for the huffman tree
 */
public class HuffmanNode {
    private byte character;
    private int frequency;
    private HuffmanNode left, right;

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode(byte character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode(byte character, HuffmanNode left, HuffmanNode right) {
        this(character, 0, left, right);
    }

    public HuffmanNode(byte character, int frequency) {
        this(character, frequency, null, null);
    }

    public HuffmanNode(byte c) {
        this(c, 0);
    }

    public byte getCharacter() {
        return this.character;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void incrementFrequency() {
        this.frequency += 1;
    }

    public HuffmanNode getLeft() {
        return this.left;
    }

    public HuffmanNode getRight() {
        return this.right;
    }

    public boolean isLeafNode() {
        return this.left == null;
    }
}