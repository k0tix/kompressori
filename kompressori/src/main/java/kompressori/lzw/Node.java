package kompressori.lzw;

/**
 * Class for a node in LZW dictionary
 */
public class Node {
    private int index;
    private byte value;
    private Node child, first;

    public Node(int index, byte value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public byte getValue() {
        return this.value;
    }

    /**
     * Add a new child to node
     * @param index
     * @param character
     */
    public void addChild(int index, byte value) {
        Node child = new Node(index, value);
        if (first != null) {
            child.child = first;
        }

        first = child;
    }

    /**
     * Get a child with byte value
     * @param value
     * @return
     */
    public Node getChild(byte value) {
        Node child = first;
        while (child != null) {
            if (child.getValue() == value) {
                return child;
            }
            child = child.child;
        }

        return null;
    }
}