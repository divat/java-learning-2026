package com.diva.basics.executorservice2026;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo1 {

    public static void main(String[] args) {
        
        Executor executor = Executors.newSingleThreadExecutor();

        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() +" generating the audit log.");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(Thread.currentThread().getName() +" completed the audit log generation.");
        };

        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() +" sending notification.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() +" notification has been sent.");
        };

        executor.execute(task1);
        executor.execute(task2);

    }


}
