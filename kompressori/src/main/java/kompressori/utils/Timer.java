package kompressori.utils;

/**
 * Helper class for timing encoding and decoding
 */
public class Timer {
    private long startTime;
    private long stopTime;

    public Timer() {
        this.startTime = 0;
        this.stopTime = 0;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }

    public long getTime() {
        return this.stopTime - this.startTime;
    }

    public void reset() {
        this.startTime = 0;
        this.stopTime = 0;
    }
}