package ratelimiter;

import strategy.IRateLimiterStrategy;

public class RateLimiter {

    private IRateLimiterStrategy strategy;

    public RateLimiter(IRateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allowRequest() {
        return strategy.allowRequest();
    }
}