package com.diva.basics.thread2026;

public class ThreadDemo {

    public static void main(String[] args)  throws InterruptedException{
       
        basicThreadDemo();

        Runnable task = () -> {
            try {
                System.out.println("Worker started");

                Thread.sleep(3000);

                System.out.println("Worker completed");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread worker = new Thread(task, "worker-thread-1");

        System.out.println("1. " +worker.getState());

        worker.start();

        System.out.println("2. " +worker.getState());

        Thread.sleep(500);

        System.out.println("3. " +worker.getState());

        worker.join();

        System.out.println("4. " +worker.getState());
    }

    public static void basicThreadDemo() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task executing on :: " +Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(task, "worker-thread");

        System.out.println("Before start :: " +Thread.currentThread().getName());

        thread.start();

        System.out.println("After start :: " +Thread.currentThread().getName());
    }

}
