package kompressori.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

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
        assertEquals(0, this.array.size());

        this.array.add((byte) 123);
        this.array.add((byte) 10);
        this.array.add((byte) 45);
        this.array.add((byte) 12);

        assertEquals(4, this.array.size());

        this.array.add((byte) 123);
        this.array.add((byte) 10);
        this.array.add((byte) 45);
        this.array.add((byte) 12);

        assertEquals(8, this.array.size());

        this.array.freeArray();
        assertEquals(0, this.array.size());
    }

    @Test
    public void initialisesCorrectly() {
        ByteArray first = new ByteArray(new byte[]{1, 2, 3, 4});
        assertEquals((byte) 1, first.get(0));

        ByteArray second = new ByteArray(first);
        assertEquals((byte) 3, second.get(2));
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
        assertEquals((byte) 123, this.array.get());
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

    @Test
    public void setsAndGetsCorrectInteger() {
        this.array.addInt(42);
        assertEquals(4, this.array.size());
        assertEquals(42, this.array.getInt());
        assertEquals(42, this.array.getInt(0));
        assertEquals(-1, this.array.getInt());

    }
}
