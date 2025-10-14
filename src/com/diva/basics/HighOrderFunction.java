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
    }

    static String repeatAction(String s, Action action) {
       return action.perform(s);
    }
    

}

interface Action {
    String perform(String s);
}
