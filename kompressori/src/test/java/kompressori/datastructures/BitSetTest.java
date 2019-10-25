package kompressori.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BitSetTest {
    private BitSet bitset;

    @Before
    public void setup() {
        this.bitset = new  BitSet();
    }

    @Test
    public void sizeIncrements() {
        assertEquals(0, this.bitset.size());
        this.bitset.set(0, true);
        this.bitset.set(1, false);
        assertEquals(2, this.bitset.size());
    }

    @Test
    public void removingLastBitDecreasesSize() {
        this.bitset.set(0, false);
        this.bitset.set(1, false);
        this.bitset.set(2, true);
        this.bitset.set(3, true);
        this.bitset.set(4, false);
        this.bitset.set(5, true);
        this.bitset.clear(2);
        assertEquals(6, this.bitset.size());
        this.bitset.clear(5);
        assertEquals(5, this.bitset.size());
    }

    @Test
    public void bitsSetCorrectly() {
        this.bitset.set(0, false);
        this.bitset.set(1, false);
        this.bitset.set(2, true);
        this.bitset.set(3, true);
        this.bitset.set(4, false);
        this.bitset.set(5, true);
        this.bitset.set(6, false);
        this.bitset.set(7, true);
        this.bitset.set(8, false);
        this.bitset.set(9, false);
        this.bitset.set(10, false);
        this.bitset.set(11, true);
        this.bitset.set(12, true);
        this.bitset.set(13, false);
        this.bitset.set(14, true);
        this.bitset.set(15, false);
        this.bitset.set(16, true);
        this.bitset.set(17, false);

        assertFalse(this.bitset.get(0));
        assertFalse(this.bitset.get(1));
        assertTrue(this.bitset.get(2));
        assertTrue(this.bitset.get(3));
        assertFalse(this.bitset.get(4));
        assertTrue(this.bitset.get(5));
        assertFalse(this.bitset.get(6));
        assertTrue(this.bitset.get(7));
        assertFalse(this.bitset.get(8));
        assertFalse(this.bitset.get(9));
        assertFalse(this.bitset.get(10));
        assertTrue(this.bitset.get(11));
        assertTrue(this.bitset.get(12));
        assertFalse(this.bitset.get(13));
        assertTrue(this.bitset.get(14));
        assertFalse(this.bitset.get(15));
        assertTrue(this.bitset.get(16));
        assertFalse(this.bitset.get(17));
    }

    @Test
    public void firstBitIsSet() {
        assertFalse(this.bitset.get(0));
        this.bitset.set(0, true);
        assertTrue(this.bitset.get(0));
    }
}