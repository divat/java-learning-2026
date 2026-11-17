package com.diva.basics.executorservice2026;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {

    public static void main(String[] args) throws InterruptedException{
        
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task1 = () -> {

            try{
                Thread.sleep(5000);
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return "Task1";
        };

        Callable<List<String>> empList = () -> {
           return empList();
        };

        Future<String> task1Sub = executor.submit(task1);

        Future<List<String>> task2Sub = executor.submit(empList);

        System.out.println(task1Sub.get());

        System.out.println(task2Sub.get());

    }

    private static List<String> empList() {
        return Arrays.asList("Emp1", "Emp2", "Emp3");
    }

}
