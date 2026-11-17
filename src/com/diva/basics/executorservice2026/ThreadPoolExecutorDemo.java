package com.diva.basics.executorservice2026;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        // Step 1: Create one shared product processing pool
        ThreadPoolExecutor executor = createProductExecutor();

        // Step 2: Keep running tasks waiting until all submissions finish
        CountDownLatch startGate = new CountDownLatch(1);

        AtomicInteger rejectedTasks = new AtomicInteger(0);

        // Step 3: Submit eight product processing tasks
        submitTask(executor, 1, "Generate Product Audit", startGate, rejectedTasks);

        submitTask(executor, 2, "Send Product Notification", startGate, rejectedTasks);

        submitTask(executor, 3, "Update Product Analytics", startGate, rejectedTasks);

        submitTask(executor, 4, "Generate Product Report", startGate, rejectedTasks);

        submitTask(executor, 5, "Update Product Search Index", startGate, rejectedTasks);

        submitTask(executor, 6, "Synchronize Product Metadata", startGate, rejectedTasks);

        submitTask(executor, 7, "Generate Inventory Summary", startGate, rejectedTasks);

        submitTask(executor, 8, "Update Product Dashboard", startGate, rejectedTasks);

        // Step 4: Display pool information before allowing tasks to proceed
        System.out.println("\n========== POOL STATUS ==========");

        printPoolStats(executor);

        System.out.println("Rejected tasks: " + rejectedTasks.get());

        // Step 5: Graceful shutdown
        executor.shutdown();

        // Step 6: Release the running tasks
        System.out.println("\nReleasing all running tasks...");
        startGate.countDown();

        // Step 7: Wait for accepted tasks to finish
        try {

            boolean completed = executor.awaitTermination(
                    20,
                    TimeUnit.SECONDS
            );

            if (completed) {
                System.out.println("\nAll accepted tasks completed.");
            } else {
                System.out.println("\nTimeout! Forcing shutdown.");

                executor.shutdownNow();
            }

        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();

            System.out.println("Main thread was interrupted.");
        }

        System.out.println("\n========== FINAL POOL STATUS ==========");

        printPoolStats(executor);
    }

    // Create the configured thread pool
    private static ThreadPoolExecutor createProductExecutor() {

        return new ThreadPoolExecutor(
                2,                              // Core pool size
                4,                              // Maximum pool size
                30,                             // Keep-alive time
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),    // Queue capacity
                new CustomThreadFactory("product-worker-"),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    // Submit a product processing task
    private static void submitTask(
            ThreadPoolExecutor executor,
            int taskId,
            String taskName,
            CountDownLatch startGate,
            AtomicInteger rejectedTasks) {

        Runnable task = () -> {

            String workerName = Thread.currentThread().getName();

            System.out.println(
                    "STARTED | Task " + taskId +
                    " | " + taskName +
                    " | Worker: " + workerName
            );

            try {

                // Wait until main thread releases the tasks
                startGate.await();

                // Simulate five seconds of background processing
                Thread.sleep(5000);

                System.out.println(
                        "COMPLETED | Task " + taskId +
                        " | " + taskName +
                        " | Worker: " + workerName
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        "INTERRUPTED | Task " + taskId +
                        " | " + taskName +
                        " | Worker: " + workerName
                );
            }
        };

        try {

            executor.execute(task);

            System.out.println("SUBMITTED | Task " + taskId + " | " + taskName);

        } catch (RejectedExecutionException e) {

            rejectedTasks.incrementAndGet();

            System.out.println(
                    "REJECTED | Task " + taskId +
                    " | " + taskName +
                    " | Reason: Pool and queue are full"
            );
        }
    }

    // Monitor thread pool statistics
    private static void printPoolStats(ThreadPoolExecutor executor) {

        System.out.println("Core pool size: " +
                executor.getCorePoolSize());

        System.out.println("Maximum pool size: " +
                executor.getMaximumPoolSize());

        System.out.println("Current pool size: " +
                executor.getPoolSize());

        System.out.println("Active threads: " +
                executor.getActiveCount());

        System.out.println("Tasks waiting in queue: " +
                executor.getQueue().size());

        System.out.println("Completed tasks: " +
                executor.getCompletedTaskCount());

        System.out.println("Shutdown initiated: " +
                executor.isShutdown());

        System.out.println("Terminated: " +
                executor.isTerminated());

        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Processors available to the JVM: " + cpuCores);
    }
}

