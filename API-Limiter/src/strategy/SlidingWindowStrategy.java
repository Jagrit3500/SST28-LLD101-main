package strategy;

import java.util.LinkedList;

public class SlidingWindowStrategy implements IRateLimiterStrategy {

    private int limit;
    private long windowSizeMillis;

    private LinkedList<Long> timestamps;

    public SlidingWindowStrategy(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.timestamps = new LinkedList<>();
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        while (!timestamps.isEmpty() &&
                now - timestamps.peekFirst() > windowSizeMillis) {
            timestamps.removeFirst();
        }

        if (timestamps.size() < limit) {
            timestamps.addLast(now);
            return true;
        }

        return false;
    }
}