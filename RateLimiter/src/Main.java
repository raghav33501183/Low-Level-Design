import service.FixedWindowRateLimiter;
import service.RateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new FixedWindowRateLimiter(5, 10000); // 5 req per 10 seconds
        String userId = "user123";

        for (int i = 0; i < 7; i++) {
            boolean allowed = rateLimiter.isAllowed(userId);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(1000);
        }
    }
}