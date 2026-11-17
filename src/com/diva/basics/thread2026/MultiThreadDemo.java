package com.diva.basics.thread2026;

public class MultiThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        long startSeconds = start/1000;

        Thread dbThread = new Thread(
            () -> performTask("Database operation")
            , "DB-Thread");

        Thread paymentThread = new Thread(
            () -> performTask("Payment operation")
            , "Payment-Thread");


         Thread auditThread = new Thread(
            () -> performTask("Audit operation")
            , "Audit-Thread");


        dbThread.start();
        paymentThread.start();
        auditThread.start();

        dbThread.join();
        paymentThread.join();
        auditThread.join();

        long end = System.currentTimeMillis();
        long endSeconds = end/1000;

        System.out.println("Total time: " + (endSeconds - startSeconds));
            
    }

    private  static void performTask(String taskName) {
       System.out.println(
            Thread.currentThread().getName()
                + " executing " + taskName
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
