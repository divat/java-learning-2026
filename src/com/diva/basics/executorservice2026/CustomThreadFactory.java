package com.diva.basics.executorservice2026;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger counter = new AtomicInteger(1);

    private final String prefix;

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable task) {
       Thread thread = new Thread(
            task, 
            prefix + counter.getAndIncrement()
        );

        return  thread;
    }

    
}
