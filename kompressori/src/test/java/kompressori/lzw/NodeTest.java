package kompressori.lzw;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {
    private Node node;

    @Before
    public void setUp() {
        this.node = new Node(4, (byte) 42);
    }

    @Test
    public void returnsCorrectIndex() {
        assertEquals(4, this.node.getIndex());
    }

    @Test
    public void returnsCorrectValue() {
        assertEquals((byte) 42, this.node.getValue());
    }

    @Test
    public void childAdding() {
        Node child = new Node(6, (byte) 119);
        this.node.addChild(child.getIndex(), child.getValue());
        assertEquals(6, node.getChild((byte) 119).getIndex());
    }
    
}