package com.diva.basics;

public class LoopsDemo {

    public static void main(String[] args) {
        //printTriangle();
        printEvenNumbers();
        //printNumbersReverse();
        //countEvenNumbers();
    }

    private static void printTriangle(){
        for(int i=0; i <= 5 ; i++){
            for(int j=0; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //Print even numbers 1 to 20
    private static void printEvenNumbers() {
        for(int i=2; i <=20; i = i + 2) {
            System.out.println(i);
            //System.out.println(i);
        }
    }

    //Print numbers 10 to 1 reverse
    private static void printNumbersReverse() {
        for(int i = 10; i >= 1; i--) {
            System.out.println(i);
        }
    }

    private static void countEvenNumbers() {
        int[] numArr = {1, 3, 4, 5, 8, 12, 15};
        int count = 0;

        for(int i=0; i < numArr.length; i++) {
            if(numArr[i] % 2 == 0) {
                count++;
                //System.out.println("Even numbers :: " +numArr[i]);
            }
        }
        System.out.println("Even numbers count :: " +count);
    }

    
}
