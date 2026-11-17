package com.diva.basics;

public class HighOrderFunction {

    public static void main(String ... args){
        String upperCase = repeatAction("Hello", new Action() {
            public String perform(String s) {
                //System.out.println(s.toUpperCase());
                return s.toUpperCase();
            }
        });

        System.out.println("=====>" +upperCase);

        int sum = processComputation(new Operation(){
            @Override
            public int compute(int a, int b) {
                return a + b; // behavior defined here
            }
        }, 4, 2);

        System.out.println(sum);
    
    }

    static String repeatAction(String s, Action action) {
       return action.perform(s);
    }
    
    static int processComputation(Operation op, int a, int b) {
        return op.compute(a, b);
    }
}

interface Action {
    String perform(String s);
}

interface Operation {
    int compute(int a, int b);
}
