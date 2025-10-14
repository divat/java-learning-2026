package com.diva.basics.java8.functionalprogramming;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        //If we are sure that always contains non-null value
        Optional<String> optString = Optional.of("jrhgjh");
        //System.out.println(optString.get());

        //Can contain null or non null value
        Optional<String> optStrNull = getString();
        optStrNull.ifPresent(s -> {
            System.out.println(optStrNull.get());
        });

        String s = getString().get();
        System.out.println(s);
        
    }

    private static Optional<String> getString() {
        return null;
    }

}
