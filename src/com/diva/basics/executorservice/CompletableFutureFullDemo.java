package com.diva.basics.executorservice;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * CompletableFutureFullDemo
 *
 * Demonstrates (in one runnable file):
 *  - custom Executor for async work
 *  - supplyAsync / runAsync
 *  - thenApply / thenAccept / thenRun
 *  - thenCompose (flat-map async)
 *  - thenCombine (merge two async results)
 *  - allOf / anyOf (fan-in/fan-out)
 *  - exception handling (exceptionally, handle, whenComplete)
 *  - timeout wrapper via ScheduledExecutorService
 *  - cancellation and interrupt cooperation
 *  - monitoring ThreadPoolExecutor stats
 *
 * Run: javac CompletableFutureFullDemo.java && java CompletableFutureFullDemo
 */
public class CompletableFutureFullDemo {

    // Custom executor for async (important: don't use commonPool for blocking tasks)
    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(
            6,                 // core threads
            12,                // max threads
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100), // bounded queue
            new NamedThreadFactory("app-pool"),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    // Scheduler used for timeouts
   // private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(2,
     //       new NamedThreadFactory("scheduler"));

    private static final Random RNG = new Random();

    public static void main(String[] args) {
        System.out.println("Main start on: " + Thread.currentThread().getName());

        // 1) Simple async work with supplyAsync (returns value)
        CompletableFuture<String> cfUser = CompletableFuture.supplyAsync(() -> fetchUser(1), POOL)
                .thenApply(user -> {
                    log("thenApply - transform user: " + user);
                    return user.toUpperCase();
                });

        // 2) thenCompose - dependent async call (user -> orders)
        CompletableFuture<String> cfOrders = cfUser.thenCompose(userId ->
                CompletableFuture.supplyAsync(() -> fetchOrders(userId), POOL)
        );

        // 3) thenCombine - combine independent async results (user + orders)
        CompletableFuture<String> combined = cfUser.thenCombine(cfOrders, (user, orders) -> {
            log("thenCombine - merging");
            return "AGG[" + user + " | " + orders + "]";
        });

       /*  // 4) Fan-out: call 3 external services in parallel and aggregate with allOf
        CompletableFuture<String> svcA = CompletableFuture.supplyAsync(() -> callService("A", 300, false), POOL);
        CompletableFuture<String> svcB = CompletableFuture.supplyAsync(() -> callService("B", 800, false), POOL);
        CompletableFuture<String> svcC = CompletableFuture.supplyAsync(() -> callService("C", 200, true), POOL); // C may fail
        CompletableFuture<Void> all = CompletableFuture.allOf(svcA, svcB, svcC);

        CompletableFuture< List<String> > aggregated = all.thenApply(v -> {
            // join each child (safe because allOf completed)
            return Arrays.asList(svcA, svcB, svcC).stream()
                    .map(cf -> {
                        try { return cf.join(); }
                        catch (CompletionException ex) { return "ERROR:" + ex.getCause().getMessage(); }
                    })
                    .collect(Collectors.toList());
        });

        // 5) anyOf - fastest result wins
        CompletableFuture<Object> fastest = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> callService("fast-1", 600, false), POOL),
                CompletableFuture.supplyAsync(() -> callService("fast-2", 300, false), POOL),
                CompletableFuture.supplyAsync(() -> callService("fast-3", 900, false), POOL)
        );

        // 6) Exception handling with exceptionally / handle / whenComplete
        CompletableFuture<String> mightFail = CompletableFuture.supplyAsync(() -> unreliableService("X", 400), POOL)
                .exceptionally(ex -> {
                    log("exceptionally recovered from: " + ex.getMessage());
                    return "fallback-X";
                })
                .whenComplete((res, ex) -> {
                    if (ex != null) log("whenComplete saw exception: " + ex.getMessage());
                    else log("whenComplete result: " + res);
                });

        // 7) Timeout wrapper example (applyToEither with a timeout future)
        CompletableFuture<String> slow = CompletableFuture.supplyAsync(() -> blockingService("SLOW", 2000), POOL);
        CompletableFuture<String> slowWithTimeout = withTimeout(slow, 700, TimeUnit.MILLISECONDS, SCHEDULER)
                .exceptionally(ex -> "TIMED_OUT");

        // 8) Cancellation demo: start long work and cancel later
        CompletableFuture<String> longTask = CompletableFuture.supplyAsync(() -> interruptibleWork(5000), POOL);
        // schedule cancellation after 1s
        SCHEDULER.schedule(() -> {
            boolean cancelled = longTask.cancel(true); // attempt interrupt
            log("requested cancellation -> " + cancelled + " (may interrupt running task)");
        }, 1, TimeUnit.SECONDS);

        // 9) Monitor pool stats asynchronously while tasks run
        CompletableFuture<Void> monitor = CompletableFuture.runAsync(() -> monitorPool(300), POOL);

        // 10) Combine and wait for results in a non-blocking composition, then join at the end
        CompletableFuture<Void> finalFlow = CompletableFuture.allOf(combined, aggregated, fastest, mightFail, slowWithTimeout, longTask)
                .thenRun(() -> log("All main tasks finished"));

        // Wait for everything (for demo purposes) - use join not get to avoid checked exceptions here
        finalFlow.join();
        log("finalFlow completed");
*/
        // show aggregated results (safe to join now)
       // log("combined => " + combined.join());
       // log("aggregated => " + aggregated.join());
       // log("fastest => " + fastest.join());
       // log("mightFail => " + mightFail.join());
        //log("slowWithTimeout => " + slowWithTimeout.join());
        //log("longTask is done? " + longTask.isDone() + ", cancelled? " + longTask.isCancelled());

        // shutdown executors
        shutdownAndAwait(POOL);
        //shutdownAndAwait(SCHEDULER);

        log("Main exit");
    }

    // ---------- helpers & simulated services ----------

    private static String fetchUser(int id) {
        log("fetchUser(" + id + ")");
        sleepRnd(150, 250);
        return "user-" + id;
    }

    private static String fetchOrders(String user) {
        log("fetchOrders(" + user + ")");
        sleepRnd(200, 400);
        return "orders-of-" + user;
    }

    private static String callService(String name, int baseMs, boolean sometimesFail) {
        log("callService " + name + " start");
        sleepRnd(baseMs, baseMs + 200);
        if (sometimesFail && RNG.nextBoolean()) {
            throw new RuntimeException("service-" + name + "-failed");
        }
        String r = name + "-OK";
        log("callService " + name + " done -> " + r);
        return r;
    }

    private static String unreliableService(String id, int ms) {
        log("unreliableService " + id + " start");
        sleep(ms);
        if (RNG.nextDouble() < 0.6) throw new RuntimeException("boom-" + id);
        return id + "-OK";
    }

    private static String blockingService(String id, int ms) {
        log("blockingService " + id + " sleeping " + ms + "ms");
        sleep(ms);
        return id + "-DONE";
    }

    // interruptible work cooperates with interruption
    private static String interruptibleWork(int ms) {
        log("interruptibleWork start for " + ms + "ms (will check interruption)");
        int waited = 0;
        while (waited < ms) {
            if (Thread.currentThread().isInterrupted()) {
                log("interruptibleWork detected interruption at " + waited + "ms");
                throw new CancellationException("interrupted");
            }
            sleep(100);
            waited += 100;
        }
        log("interruptibleWork completed");
        return "long-done";
    }

    // Timeout helper: returns a CF that completes with the original result if it finishes before timeout,
    // otherwise completes exceptionally with TimeoutException
    private static <T> CompletableFuture<T> withTimeout(CompletableFuture<T> cf, long timeout, TimeUnit unit, ScheduledExecutorService scheduler) {
        CompletableFuture<T> timeout1 = new CompletableFuture<>();
        scheduler.schedule(() -> timeout1.completeExceptionally(new TimeoutException("timeout after " + timeout1 + " " + unit)), timeout1, unit);
        return cf.applyToEither(timeout1, t -> t);
    }

    // Monitor pool stats - prints a few snapshots
    private static void monitorPool(int intervalMillis) {
        for (int i = 0; i < 6; i++) {
            if (POOL instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor p = POOL;
                log(String.format("POOL STAT - poolSize=%d active=%d queued=%d completed=%d",
                        p.getPoolSize(), p.getActiveCount(), p.getQueue().size(), p.getCompletedTaskCount()));
            }
            sleep(intervalMillis);
        }
    }

    // Named thread factory
    static class NamedThreadFactory implements ThreadFactory {
        private final String prefix;
        private final AtomicInteger idx = new AtomicInteger(0);
        NamedThreadFactory(String prefix) { this.prefix = prefix; }
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, prefix + "-" + idx.incrementAndGet());
            t.setDaemon(false);
            return t;
        }
    }

    // utility sleep helpers
    private static void sleepRnd(int minMs, int maxMs) {
        int ms = minMs + RNG.nextInt(Math.max(1, maxMs - minMs));
        sleep(ms);
    }

    private static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) { /* keep going or respond to interruption in tasks */ }
    }

    private static void log(String s) {
        System.out.println("[" + System.currentTimeMillis() + "][" + Thread.currentThread().getName() + "] " + s);
    }

    private static void shutdownAndAwait(ExecutorService es) {
        log("shutting down " + es);
        es.shutdown();
        try {
            if (!es.awaitTermination(5, TimeUnit.SECONDS)) {
                log("forcing shutdown " + es);
                es.shutdownNow();
                es.awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            es.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
