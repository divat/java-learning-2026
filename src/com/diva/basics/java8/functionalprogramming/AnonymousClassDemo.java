package com.diva.basics.java8.functionalprogramming;

import java.util.function.Predicate;

public class AnonymousClassDemo {

    public static void main(String[] args) {
        
        processPayment(4000.223, new PaymentStrategy() {
            @Override
            public void pay(double amout) {
                System.out.println("Processed payment of Rs." +amout+" using Credit Card.");
            }
        });

        processPayment(242432.73, new PaymentStrategy() {
            @Override
            public void pay(double amout) {
                System.out.println("Processed payment of Rs." +amout+" using PayPal.");
            }
        });
        
        PaymentStrategy p = (amt) -> System.out.println("Processed payment of Rs."+amt+"using UPI payment"); 
        p.pay(2424.108);

    }

    private static void processPayment(double amount, PaymentStrategy p) {
        p.pay(amount);
    }

}

@FunctionalInterface
interface PaymentStrategy {

    /**
     * It can accept any type of amount
     * @param amount
     */
    void pay(double amount); 
}
