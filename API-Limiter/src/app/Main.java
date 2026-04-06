package app;

import api.*;
import proxy.*;
import ratelimiter.*;
import strategy.*;
import service.*;
import controller.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Strategy
        IRateLimiterStrategy strategy = new FixedWindowStrategy(3, 5000);
        // Try SlidingWindowStrategy also

        // RateLimiter
        RateLimiter rateLimiter = new RateLimiter(strategy);

        // Real API
        IRemoteAPI realAPI = new RealRemoteAPI();

        // Proxy (IMPORTANT)
        IRemoteAPI proxyAPI = new RateLimitedProxy(realAPI, rateLimiter);

        // Service
        MyService service = new MyService(proxyAPI);

        // Controller
        MyController controller = new MyController(service);

        // Simulate requests
        for (int i = 1; i <= 6; i++) {
            System.out.println("Request " + i);
            controller.handleRequest(true);
        }
    }
}