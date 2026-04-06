package strategy;

public interface IRateLimiterStrategy {
    boolean allowRequest();
}