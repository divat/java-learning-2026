package com.diva.basics.thread2026;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException{  
        Inventory inventory = new Inventory();
        
        Runnable purchaseTask = () -> inventory.purchase();

        Thread customerA = new Thread(purchaseTask, "Customer-A");
        Thread customerB = new Thread(purchaseTask, "Customer-B");

        customerA.start();
        customerB.start();

        customerA.join();
        customerB.join();

    }

}

class Inventory {
    private int stock = 1;

    private final ReentrantLock lock = new ReentrantLock();

    public void purchase() {
        lock.lock();

        try {
            if(stock > 0) {
                System.out.println(Thread.currentThread().getName() +" is purchasing stock: " +stock);

                 stock--;

                System.out.println("Remaining stock available :: " +stock);
            } else {
                System.out.println("Stock not available.");
            }

           
        } finally {
            lock.unlock();
        }
    }
}
