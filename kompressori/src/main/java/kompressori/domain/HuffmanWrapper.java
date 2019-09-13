package kompressori.domain;

/**
 * Wrapper for holding the created huffman tree and
 * the encoded string
 */
public class HuffmanWrapper {
    private HuffmanNode tree;
    private String encodedString;

    public HuffmanWrapper(HuffmanNode tree, String encodedString) {
        this.tree = tree;
        this.encodedString = encodedString;
    }

    public HuffmanNode getTree() {
        return this.tree;
    }

    public String getEncodedString() {
        return this.encodedString;
    }
}