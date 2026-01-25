package com.diva.basics.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    //static int sharedCounter = 0;

    static AtomicInteger sharedCounter = new AtomicInteger(0);

    public static void main(String[] args) {

        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            for(int i=0; i<3; i++) {
                sharedCounter.incrementAndGet();
                System.out.println(name + " incremented counter to " + sharedCounter);
            }
        };

        Thread t1 = new Thread(task, "Worker-1");
        Thread t2 = new Thread(task, "Worker-2");

        t1.start();
        t2.start();

    }
}
