package kompressori.datastructures;

/**
 * Simple byte array class
 */
public class ByteArray {
    private byte[] array;
    private int size;
    private int pointer;

    public ByteArray(int size) {
        this.array = new byte[size];
        this.size = 0;
        this.pointer = 0;
    }

    public ByteArray(byte[] initialBytes) {
        this.array = initialBytes;
        this.size = initialBytes.length - 1;
        this.pointer = 0;
    }

    public ByteArray(ByteArray initial) {
        this(initial.array());
    }

    /**
     * Add a new byte to the array
     * @param b
     */
    public void add(byte b) {
        if (this.size == this.array.length) {
            this.array = this.resize();
        }

        this.array[this.size++] = b;
    }

    /**
     * Add a 4 byte integer to the array
     * @param i
     */
    public void addInt(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((i >>> 0) & 0xff);
        bytes[1] = (byte) ((i >>> 8) & 0xff);
        bytes[2] = (byte) ((i >>> 16) & 0xff);
        bytes[3] = (byte) ((i >>> 24) & 0xff);
        this.addAll(bytes);
    }

    /**
     * Adds a LZW 2 byte index to the array. Used in LZW encoding
     * @param index
     */
    public void addLZWindex(int index) {
        int end = index >> 8;
        this.add((byte) end);
        this.add((byte) index);
    }

    /**
     * Add bytes from byte array
     * @param bytes
     */
    public void addAll(byte[] bytes) {
        for (byte b : bytes) {
            this.add(b);
        }
    }

    public byte get(int index) {
        return this.array[index];
    }

    public byte get() {
        return this.array[pointer++];
    }

    /**
     * Returns next 4 byte integer at index from byte array
     * @param index
     * @return
     */
    public int getInt(int index) {
        int i = ((this.array[index] & 0xFF) << 24) | 
            ((this.array[index + 1] & 0xFF) << 16) | 
            ((this.array[index + 2] & 0xFF) << 8 ) | 
            ((this.array[index + 3] & 0xFF) << 0 );

        return i;
    }

    /**
     * Returns next 4 byte integer at pointer from byte array
     * @return integer value or -1 if not enough bytes
     */
    public int getInt() {
        if (this.pointer >= this.array.length) {
            return -1;
        }

        int i = ((this.array[pointer] & 0xFF) << 24) | 
            ((this.array[pointer + 1] & 0xFF) << 16) | 
            ((this.array[pointer + 2] & 0xFF) << 8 ) | 
            ((this.array[pointer + 3] & 0xFF) << 0 );

        this.pointer += 4;
        return i;
    }

    /**
     * Sets a byte at index
     * @param index
     * @param b
     */
    public void set(int index, byte b) {
        this.array[index] = b;
    }

    public int size() {
        return this.size;
    }

    /**
     * Resizes the array to hold more bytes
     * @return
     */
    private byte[] resize() {
        byte[] newArray = new byte[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        return newArray;
    }

    /**
     * Return a primitive byte array holding all the byte values
     * @return
     */
    public byte[] array() {
        byte[] result = new byte[this.size];

        for (int i = 0; i < this.size; i++) {
            result[i] = this.array[i];
        }

        return result;
    }

    /**
     * Free all bytes by allowing
     * to write new bytes on top of the
     * old ones
     */
    public void freeArray() {
        this.size = 0;
    }

    /**
     * Clones the current ByteArray Object to a new one
     */
    public ByteArray clone() {
        byte[] data = this.array();
        ByteArray clone = new ByteArray(1);
        clone.array = data;
        clone.size  = this.size;
        return clone;
    }

    public ByteArray concat(byte b) {
        ByteArray newArray = this.clone();
        newArray.add(b);
        return newArray;
    }
}