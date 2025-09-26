package com.diva.basics;

import java.util.Scanner;

public class MathematicalPrograms {

    public static void main(String[] args) {
        isPrimeNumber();

        wordsCount();
    }

    private static boolean isPrimeNumber() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number");
        int num = scanner.nextInt();

        boolean isPrime = true;

        if (num <=1 ) {
            isPrime = false;
        } else {
            for(int i = 2; i * i <= num; i++){
                if(num % i == 0){
                    isPrime = false;
                    break;
                }
            }
        }

        System.out.println(num + (isPrime ? " is Prime" : " is NOT Prime"));
        scanner.close();
        return isPrime;
    }

    //Start with 0,1. Next number = Sum of previous one. Iteratively update values
    private static void fibonacciSeries(int num){
        int first = 0, second = 1;

        for(int i = 0; i < num; i++) {
            System.out.println("Fibonacci ::" +first+" "+second);
            int next = first + second; //1
            first = second; //1
            second = next; //2
        }
    }

    private static void factorial(int n) {
        long fact = 1;

        for(int i=0; i<n; i++){
            fact = fact * i;
        }

        System.out.println("Factorial of " + n + "=" +fact);
    }

}
