package com.diva.basics.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCreation {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Main thread :: " +Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        myThread.start();

        Thread task = new Thread(new MyTask());
        task.start();

        //Executor service
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> result = executor.submit(new MyTask1());
        System.out.println(result.get());
    }

}

class MyThread extends Thread {
    public void run() {
        System.out.println("Creating a new thread :: " +Thread.currentThread().getName());
    }
}

class MyTask implements Runnable {
    public void run(){
        System.out.println("Creating a new thread :: " +Thread.currentThread().getName());
    }
}

class MyTask1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Another thread using callable :: " +Thread.currentThread().getName();
    }
    
}