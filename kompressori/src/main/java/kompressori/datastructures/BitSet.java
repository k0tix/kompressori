package kompressori.datastructures;

public class BitSet {
    private int size;
    private byte[] buffer;

    public BitSet() {
        this.size = 0;
        this.buffer = new byte[1];
    }

    public BitSet(int size, byte[] buffer) {
        this.size = size;
        this.buffer = buffer;
    }

    public void set(int index, boolean bit) {
        int position = index / 8;

        if (position >= buffer.length) {
            resize(position);
        }

        int bytePosition = index - position * 8;
        
        this.buffer[position] = bit 
            ? (byte) (this.buffer[position] | (1 << bytePosition)) 
            : (byte) (this.buffer[position] & ~(1 << bytePosition));

        size = size <= index ? index + 1 : size;
    }

    public boolean get(int index) {
        int position = index / 8;
        int bytePosition = index - position * 8;
        return ((this.buffer[position] >> bytePosition) & 1) == 1;
    }

    public void clear(int index) {
        set(index, false);
        size = size - 1 == index ? size - 1 : size;
    }

    public BitSet clone() {
        int cloneSize = (size - 1) / 8 + 1;
        byte[] clone = new byte[cloneSize];
        for (int i = 0; i < clone.length; i++) {
            clone[i] = this.buffer[i];
        }
        return new BitSet(this.size, clone);
    }

    public int size() {
        return this.size;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    private void resize(int index) {
        int size = index > this.buffer.length * 2 ? index : this.buffer.length * 2;
        byte newBuffer[] = new byte[size];
        for (int i = 0; i < this.buffer.length; i++) {
            newBuffer[i] = this.buffer[i];
        }

        this.buffer =  newBuffer;
    }
}