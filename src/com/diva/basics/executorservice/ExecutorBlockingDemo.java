package com.diva.basics.executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorBlockingDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<String> task1 = pool.submit(() -> {
            System.out.println("Task 1 processing by thread " +Thread.currentThread().getName());
            Thread.sleep(3000);
            return "Task 1 done";
        });

        Future<String> task2 = pool.submit(() -> {
            System.out.println("Task 1 processing by thread " +Thread.currentThread().getName());
            Thread.sleep(4000);
            return "Task 2 done";
        });


        System.out.println("Main thread waiting for results....");
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println("All tasks done!");

        pool.shutdownNow();

    }

}
