package com.diva.basics;

public class LoopsDemo {

    public static void main(String[] args) {
        printTriangle();
    }

    private static void printTriangle(){
        for(int i=0; i <= 5 ; i++){
            for(int j=0; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
