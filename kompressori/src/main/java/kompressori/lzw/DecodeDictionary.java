package kompressori.lzw;

import kompressori.datastructures.ByteArray;

/**
 * LZW decoding dictionary that holds a list of ByteArrays
 */
public class DecodeDictionary {
    private int size;
    private ByteArray[] list;

    public DecodeDictionary() {
        this.size = 256;
        this.list = new ByteArray[1024];

        initialize();
    }

    public int size() {
        return this.size;
    }

    /**
     * Resets the dictionary keeping the initial values
     */
    public void reset() {
        this.size = 256;
    }

    public ByteArray get(int index) {
        return this.list[index];
    }

    public void add(ByteArray prefix) {
        if (this.size >= list.length) {
            resize();
        }
        this.list[this.size++] = prefix;
    }

    /**
     * Resize array to hold more values
     */
    private void resize() {
        ByteArray[] resizedList = new ByteArray[this.size * 2];
        for (int i = 0; i < this.size; i++) {
            resizedList[i] = this.list[i];
        }
        this.list = resizedList;
    }

    /**
     * Initialize starting values for dictionary
     */
    public void initialize() {        
        for (int i = 0; i < 256; i++) {
            ByteArray prefix = new ByteArray(256);
            prefix.add((byte) (-128 + i));
            this.list[i] = prefix;
        }
    }
}