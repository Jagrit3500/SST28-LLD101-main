package proxy;

import api.IRemoteAPI;
import ratelimiter.RateLimiter;

public class RateLimitedProxy implements IRemoteAPI {

    private IRemoteAPI realAPI;
    private RateLimiter rateLimiter;

    public RateLimitedProxy(IRemoteAPI realAPI, RateLimiter rateLimiter) {
        this.realAPI = realAPI;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public String call() {
        if (!rateLimiter.allowRequest()) {
            return "429 Too Many Requests";
        }
        return realAPI.call();
    }
}