package strategy;

public class FixedWindowStrategy implements IRateLimiterStrategy {

    private int limit;
    private long windowSizeMillis;

    private int counter;
    private long windowStart;

    public FixedWindowStrategy(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
        this.counter = 0;
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        if (now - windowStart >= windowSizeMillis) {
            windowStart = now;
            counter = 0;
        }

        if (counter < limit) {
            counter++;
            return true;
        }

        return false;
    }
}