package com.diva.basics.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NamingThreadFactory {

    public static void main(String[] args) {
        
        MyThreadFactory threadFactory = new MyThreadFactory("MyWorker-");
        ExecutorService executor = Executors.newFixedThreadPool(2, threadFactory);

        for(int i = 0; i < 3; i++) {
            executor.execute(()-> System.out.println(Thread.currentThread().getName()));
        }

        awaitTerminationAfterShutdown(executor);

    }

    private static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}


class MyThreadFactory implements ThreadFactory {

    private AtomicInteger threadNo = new AtomicInteger(1);
    private String threadNamePrefix = "";

    public MyThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadNamePrefix + threadNo.getAndIncrement());
    }
    
} 