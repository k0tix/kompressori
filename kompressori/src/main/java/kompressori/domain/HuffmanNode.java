package kompressori.domain;

/**
 * Class for the huffman tree
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private Character character;
    private int frequency;
    private HuffmanNode left, right;

    public HuffmanNode(Character character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode(char character, int frequency) {
        this(character, frequency, null, null);
    }

    public Character getCharacter() {
        return this.character;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public HuffmanNode getLeft() {
        return this.left;
    }

    public HuffmanNode getRight() {
        return this.right;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return this.frequency <= node.frequency ? -1 : 1;
    }
}