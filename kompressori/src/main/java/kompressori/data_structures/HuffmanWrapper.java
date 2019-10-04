package kompressori.data_structures;

import java.io.Serializable;

/**
 * Wrapper for holding the created huffman tree and
 * the encoded string
 */
public class HuffmanWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
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