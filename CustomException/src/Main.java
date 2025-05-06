import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        var type = sc.next();

        if (type.equals("COMPLETABLE_FUTURE")) {

            Supplier<Integer> supplier = () -> {
                try {
                    Thread.sleep(2000); // Simulate delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return 42;
            };

            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(supplier);

            System.out.println("Task submitted, doing other work...");

            future.thenApply(result -> {
                return result * 2; // Apply some transformation
            }).thenAccept(result -> {
                System.out.println("Processed result: " + result); // Non-blocking, prints result when ready
            });

            // Do other tasks here (non-blocking)
            System.out.println("Other work happening in parallel...");

            Thread.sleep(10000);

            return;
        }


        ExecutorService executor = Executors.newSingleThreadExecutor();

        switch (type) {
            case "RUNNABLE":
                AtomicInteger resultHolder = new AtomicInteger();
                Runnable task = () -> {
                    try {
                        Thread.sleep(2000); // simulate delay
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    resultHolder.set(43);
                };

                Future<?> future = executor.submit(task);
                System.out.println("Task submitted, doing other work...");

                future.get(); // waits for the task to finish
                System.out.println("Result: " + resultHolder.get());
                break;

            case "CALLABLE":
                Callable<Integer> task2 = () -> {
                    Thread.sleep(2000); // simulate delay
                    return 42;
                };

                Future<Integer> future2 = executor.submit(task2);
                System.out.println("Task submitted, doing other work...");

                Integer result = future2.get(); // waits for the task to finish
                System.out.println("Result: " + result);
                break;
        }

        executor.shutdown();
    }
}