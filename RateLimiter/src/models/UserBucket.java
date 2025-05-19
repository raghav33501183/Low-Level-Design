package models;

public class UserBucket {
    private int requestCount;
    private long windowStart;
    private final int limit;
    private final long intervalMillis;

    public UserBucket(int limit, long intervalMillis) {
        this.limit = limit;
        this.intervalMillis = intervalMillis;
        this.windowStart = System.currentTimeMillis();
        this.requestCount = 0;
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - windowStart > intervalMillis) {
            // Reset the window
            windowStart = now;
            requestCount = 0;
        }

        if (requestCount < limit) {
            requestCount++;
            return true;
        }
        return false;
    }
}
