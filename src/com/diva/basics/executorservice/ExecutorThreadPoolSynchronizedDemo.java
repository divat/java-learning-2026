package com.diva.basics.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorThreadPoolSynchronizedDemo {

    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=0; i<5;i++) {
            executor.submit(counter::increment);
        }

        System.out.println("Count :: " +counter.get());

        executor.shutdownNow();

    }

}

class SharedCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int get() {
        return count;
    }
}
