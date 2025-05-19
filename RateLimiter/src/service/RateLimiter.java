package service;

public interface RateLimiter {
    boolean isAllowed(String userId);
}
