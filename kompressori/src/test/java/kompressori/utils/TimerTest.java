package kompressori.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TimerTest {
    private Timer timer;

    @Before
    public void setUp() {
        this.timer = new Timer();
    }

    @Test
    public void testTiming() {
        assertEquals(0, timer.getTime());
        timer.start();
        assertNotEquals(0, timer.getTime());
    }

    @Test
    public void timerResets() {
        timer.start();
        assertNotEquals(0, timer.getTime());
        timer.stop();
        timer.reset();
        assertEquals(0, timer.getTime());
    }
}