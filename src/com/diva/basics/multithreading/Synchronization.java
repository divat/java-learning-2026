package com.diva.basics.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;

public class Synchronization {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
         Counter counter = new Counter();

         Runnable r = () -> {
            for(int i=0; i < 1000;i++){
                counter.count++;
            }
         };

         Thread t1 = new Thread(r);
         Thread t2 = new Thread(r);

         t1.start();
         t2.start();
         t1.join();
         t2.join();

         System.out.println("Value of counter :: " +counter.count);

         String s =  new String("dfdg");

         System.out.println(s.intern());
    }

}

class Counter {
    int count = 0;
}
