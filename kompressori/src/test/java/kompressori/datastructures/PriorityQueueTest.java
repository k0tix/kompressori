package kompressori.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kompressori.huffman.HuffmanNode;

public class PriorityQueueTest {
    private PriorityQueue queue;

    @Before
    public void setup() {
        this.queue = new PriorityQueue(256);
    }

    @Test
    public void sizeIncrements() {
        assertEquals(0, this.queue.size());
        this.queue.add(new HuffmanNode(1, null, null));
        this.queue.add(new HuffmanNode(1, null, null));
        assertEquals(2, this.queue.size());
    }

    @Test
    public void correctOrder() {
        this.queue.add(new HuffmanNode(1, null, null));
        this.queue.add(new HuffmanNode(4, null, null));
        this.queue.add(new HuffmanNode(1, null, null));
        this.queue.add(new HuffmanNode(8, null, null));
        this.queue.add(new HuffmanNode(4, null, null));
        this.queue.add(new HuffmanNode(10, null, null));

        assertEquals(1, this.queue.poll().getFrequency());
        assertEquals(1, this.queue.poll().getFrequency());
        assertEquals(4, this.queue.poll().getFrequency());
        assertEquals(4, this.queue.poll().getFrequency());
        assertEquals(8, this.queue.poll().getFrequency());
        assertEquals(10, this.queue.poll().getFrequency());
    }

    @Test
    public void addMultiple() {
        HuffmanNode[] list = new HuffmanNode[4];
        list[0] = new HuffmanNode(1, null, null);
        list[1] = new HuffmanNode(15, null, null);
        list[2] = new HuffmanNode(3, null, null);
        list[3] = new HuffmanNode(5, null, null);
        
        this.queue.addAll(list);
        assertEquals(4, this.queue.size());
    }
}