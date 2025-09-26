package com.diva.basics;

public class HighOrderFunction {

    public static void main(String ... args){
        repeatAction("Hello", new Action() {
            public void perform(String s) {
                System.out.println(s.toUpperCase());
            }
        });
    }

    static void repeatAction(String s, Action action) {
        action.perform(s);
    }

}

interface Action {
    void perform(String s);
}
