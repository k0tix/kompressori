package kompressori.data_structures;

/**
 * Simple byte array class
 */
public class ByteArray {

    private byte[] array;
    private int size;

    public ByteArray(int size) {
        this.array = new byte[size];
        this.size = 0;
    }

    public void add(byte b) {
        if (this.size + 1 == this.array.length) {
            this.array = this.doubleSize();
        }

        this.array[this.size] = b;
        this.size += 1;
    }

    public byte get(int index) {
        return this.array[index];
    }

    public void set(int index, byte b) {
        this.array[index] = b;
    }

    public int getSize() {
        return this.size;
    }

    private byte[] doubleSize() {
        byte[] newArray = new byte[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        return newArray;
    }

    public void freeArray() {
        this.size = 0;
    }
}