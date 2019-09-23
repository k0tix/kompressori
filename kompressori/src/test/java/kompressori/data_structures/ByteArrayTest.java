package kompressori.data_structures;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * Unit test for the ByteArray data structure
 */
public class ByteArrayTest {

    private ByteArray array;

    @Before
    public void setUp() {
        this.array = new ByteArray(2);
    }

    @Test
    /**
     * ByteArray resizes accordingly
     */
    public void correctSizing() {
        assertEquals(0, this.array.getSize());

        this.array.add((byte) 123);
        this.array.add((byte) 10);
        this.array.add((byte) 45);
        this.array.add((byte) 12);

        assertEquals(4, this.array.getSize());

        this.array.add((byte) 123);
        this.array.add((byte) 10);
        this.array.add((byte) 45);
        this.array.add((byte) 12);

        assertEquals(8, this.array.getSize());

        this.array.freeArray();
        assertEquals(0, this.array.getSize());
    }

    @Test
    /**
     * ByteArray returns correct values
     */
    public void returnsCorrectBytes() {
        this.array.add((byte) 123);
        this.array.add((byte) 2);

        assertEquals((byte) 123, this.array.get(0));
        assertEquals((byte) 2, this.array.get(1));
    }

    @Test
    /**
     * ByteArray sets correct values
     */
    public void setCorrectValues() {
        this.array.add((byte) 0);
        this.array.add((byte) 1);

        assertEquals((byte) 1, this.array.get(1));

        this.array.set(1, (byte) 25);

        assertEquals((byte) 25, this.array.get(1));
    }
}
