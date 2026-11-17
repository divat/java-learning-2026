package com.diva.basics.thread2026;

public class SharedMemoryDemo1 {
    static class Inventory {
            int stock = 10;

            public void purchase() {
                int currentStock = stock;

                // Simulate a delay between reading and writing
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                stock = currentStock - 1;
            }
        }

        public static void main(String[] args)
                throws InterruptedException {

            Inventory inventory = new Inventory();

            Thread t1 = new Thread(
                () -> inventory.purchase(), "Request-A"
            );

            Thread t2 = new Thread(
                () -> inventory.purchase(), "Request-B"
            );

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(
                "Final stock: " + inventory.stock
            );
        }
}
