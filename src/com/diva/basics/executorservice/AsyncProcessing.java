package com.diva.basics.executorservice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class AsyncProcessing {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        int[] arr = {23,57,98,75,43,97};


         // Simulate 5 providers
        List<Callable<List<Integer>>> providers = List.of(
            () -> mockProvider("Provider1", arr, 2000),  // 2 sec delay
            () -> mockProvider("Provider2", arr, 1500),  // 1.5 sec delay
            () -> mockProvider("Provider3", arr, 3000),
            () -> mockProvider("Provider4", arr, 1000),  // <== fastest
            () -> mockProvider("Provider5", arr, 2500)
        );

        // Wrap each provider in a CompletableFuture
        List<CompletableFuture<List<Integer>>> futures = providers.stream()
            .map(p -> CompletableFuture.supplyAsync(() -> {
                try { return p.call(); } catch (Exception e) { throw new RuntimeException(e); }
            }, executor))
            .collect(Collectors.toList());

        // anyOf returns the first completed one
        CompletableFuture<Object> first = CompletableFuture.anyOf(futures.toArray(new CompletableFuture[0]));

        // Get the result
        List<Integer> result = (List<Integer>) first.get();
        System.out.println("Winner response: " + result);

        executor.shutdownNow();

    }

     private static List<Integer> mockProvider(String name, int[] arr, int delay) throws InterruptedException {
        Thread.sleep(delay); // simulate API delay
        List<Integer> sorted = Arrays.stream(arr).boxed().sorted().toList();
        System.out.println(name + " responded: " + sorted);
        return sorted;
    } 
}
