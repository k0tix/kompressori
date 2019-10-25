package kompressori.lzw;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kompressori.datastructures.ByteArray;

public class DecodeDictionaryTest {
    private DecodeDictionary dict;

    @Before
    public void setUp() {
        this.dict = new DecodeDictionary();
    }

    @Test
    public void dictionaryInitializedCorrectly() {
        assertEquals(256, this.dict.size());
        assertEquals((byte) 127, this.dict.get(255).get(0));
    }

    @Test
    public void dictionaryResizesAndResets() {
        for (int i = 0; i < 1050; i++) {
            this.dict.add(new ByteArray(1));
        }

        assertEquals(1306, this.dict.size());
        this.dict.reset();
        assertEquals(256, this.dict.size());
    }
}