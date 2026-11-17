package com.diva.basics.executorservice2026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownDemo {

    public static void main(String[] args)  throws InterruptedException {
        
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " started");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException  e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println(
                Thread.currentThread().getName()
                + " completed"
            );

        };

        executor.execute(task);
        executor.execute(task);

        executor.shutdown();

        System.out.println("Waiting for tasks...");

        boolean completed = executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Completing tasks...");

        System.out.println(
            "All tasks completed: " + completed
        );

    }

}
