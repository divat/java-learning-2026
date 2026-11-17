package com.diva.basics.multithreading;

public class VolatileKeywordDemo {

    public static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Runnable stopProcess = () -> {
            
            while(running) {
                System.out.println("Working.......");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
            System.out.println("Stopped gracefully....");
        };

        Thread workerThread = new Thread(stopProcess, "Worker-1");
        workerThread.start();

        Thread.sleep(2000);

        running = false;
    }
}
