package com.diva.basics.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorThreadPoolDemo {

    private static int counter = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " processing task " + counter);
            increment();
            try {
                System.out.println("Counter value :: " +counter);
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        };
        
        for(int i = 0; i < 5; i++){
            //Expectation each worker thread should work on this task to produce the value 5
            executor.submit(task);
            
        }

        executor.shutdown();
    }

    public static void increment() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            counter++;
        } finally {
            if(lock.isLocked()) {  
                System.out.println("Current thread is locked");
                lock.unlock();
            }
        }
        
    }
    
}
