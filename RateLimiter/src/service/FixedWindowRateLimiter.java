package service;

import models.UserBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiter {
    private final Map<String, UserBucket> userBuckets = new ConcurrentHashMap<>();
    private final int limit;
    private final long intervalMillis;

    public FixedWindowRateLimiter(int limit, long intervalMillis) {
        this.limit = limit;
        this.intervalMillis = intervalMillis;
    }

    @Override
    public boolean isAllowed(String userId) {
        userBuckets.putIfAbsent(userId, new UserBucket(limit, intervalMillis));
        return userBuckets.get(userId).allowRequest();
    }
}
