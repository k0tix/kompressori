package kompressori.lzw;

import kompressori.datastructures.ByteArray;

public class EncodeDictionary {
    private int size;
    private Node[] dictionary;

    public EncodeDictionary() {
        this.size = 256;
        this.dictionary = new Node[256];
        this.initialize();
    }

    public void add(ByteArray bytes, byte value) {
        search(bytes).addChild(this.size++, value);
    }

    public boolean has(ByteArray bytes, byte b) {
        if (bytes.size() < 1) {
            return true;
        }

        Node node = search(bytes);
        
        if (node == null) {
            return false;
        }
        return node.getChild(b) != null;
    }

    public int getIndex(ByteArray bytes) {
        if (bytes.size() == 1) {
            return this.dictionary[128 + bytes.get(0)].getIndex();
        }
        Node w = search(bytes);
        return w.getIndex();
    }

    public Node search(ByteArray bytes) {
        Node node = dictionary[0xff & bytes.get(0)];
        for (int i = 1; i < bytes.size(); i++) {
            node = node.getChild(bytes.get(i));
            if (node == null) {
                return null;
            }
        }

        return node;
    }

    public int size() {
        return this.size;
    }

    public void initialize() {
        for (int i = 0; i < 256; i++) {
            this.dictionary[i] = new Node(i, (byte) (-128 + i));
        }
    }
}