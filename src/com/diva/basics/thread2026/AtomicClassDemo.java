package com.diva.basics.thread2026;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClassDemo {

    private static AtomicInteger stock = new AtomicInteger(10);

    public static void main(String[] args) throws InterruptedException{
        
        Runnable purchaseTask = () -> {
            System.out.println("Purchasing started..");

            stock.decrementAndGet();

            System.out.println(Thread.currentThread().getName() + " purchased one item stock : " +stock.get());
        };

        Thread customerA = new Thread(purchaseTask, "customerA-thread");
        Thread customerB = new Thread(purchaseTask, "customerB-thread");

        customerA.start();
        customerB.start();
        
        customerA.join();
        customerB.join();

        System.out.println(
            "Final stock: " + stock.get()
        );

    }

}
