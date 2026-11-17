package com.diva.basics.executorservice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. Use executor service execute or submit method to execute a single task
        // 2. Create the task to compute given two integer values
        // 3. Get the return value of task using Future

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

       /*Calculate add = new Calculate() {
                @Override
                public int compute(int x, int y) {
                    return x + y;
                }
        };*/

        Calculate addLambda = (x, y) -> {
            return x + y;
        };

        Calculate multiplyLambda = (x, y) -> {
            return x * y;
        };

        List<Callable<Integer>> taskList = new LinkedList<>();
        

        Callable<Integer> addValue = () -> {
            Thread.sleep(5000);
            return doMathComputation(10, 20, addLambda);
        };

        Callable<Integer> multiplyValue = () -> {
            Thread.sleep(3000);
            return doMathComputation(10, 20, multiplyLambda);
        };

        taskList.add(addValue);
        taskList.add(multiplyValue);

        //To get the single value
        Future<Integer> future = executor.submit(addValue);
        
        //Assign multiple tasks to get the single task computed fast
        Integer callable = executor.invokeAny(taskList);
        System.out.println("Completed tasks :: " +callable);
        try {
           // System.out.println("Computed value :: " + future.get());

           //Future with get() timeout function
           System.out.println("Computed value :: " + future.get(200, TimeUnit.MILLISECONDS));
           if(future.isDone()){
            System.out.println("Task executed successfully");
           }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        executor.shutdown();

    }

    private static Integer doMathComputation(int x, int y, Calculate calculate) {
        return calculate.compute(x, y);
    }

}

@FunctionalInterface
interface Calculate {
    int compute(int x, int y);
}
