import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // for hourly and daily

        // Run hourly (every 1 hour after last completes)
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Hourly job started at " + LocalTime.now());
            runParallelBatches();
        }, 0, 1, TimeUnit.HOURS);

        // Run daily (every 24 hours after last completes)
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Daily job started at " + LocalTime.now());
            runParallelBatches();
        }, 0, 24, TimeUnit.HOURS);
    }

    private static void runParallelBatches() {
        int totalIterations = 100;
        int batchSize = 10;
        int totalBatches = totalIterations / batchSize;

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int batch = 0; batch < totalBatches; batch++) {
            final int start = batch * batchSize;
            final int end = start + batchSize;

            // Run each batch asynchronously
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                for (int i = start; i < end; i++) {
                    process(i);
                }
            });

            futures.add(future);
        }

        // Wait for all batches to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        System.out.println("All batches completed at " + LocalTime.now());
    }

    private static void process(int i) {
        System.out.println("Processing iteration " + i + " on thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(100);  // Simulated work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
