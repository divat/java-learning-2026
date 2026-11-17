package com.diva.basics.thread2026;

import java.util.HashMap;
import java.util.Map;

public class SharedMemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        
        BankAccount bankAcc = new BankAccount();
        bankAcc.deposit("B00125051987", 10000);

        Runnable withdrawTask1 = new Runnable() {

            @Override
            public void run() {
               bankAcc.withdraw("B00125051987", 5000.00);
            }
            
        };

        Runnable withdrawTask2 = new Runnable() {

            @Override
            public void run() {
               bankAcc.withdraw("B00125051987", 2000.00);
            }
            
        };

        Thread task1 = new Thread(withdrawTask1, "worker-1");
        Thread task2 = new Thread(withdrawTask2, "worker-2");

        task1.start();
        task2.start();

        task1.join();
        task2.join();

        System.out.println("Final account balance :: " +bankAcc.getBalance("B00125051987"));

    }
}

class BankAccount {

    private  double amount;

    private final Map<String, Double> bankAcc = new HashMap<>();

    public double getBalance(String accountId) {
        return bankAcc.get(accountId);
    }

    public void deposit(String accId, double amount) {
        bankAcc.put(accId, amount);
    }

    public void withdraw(String accId, double amount) {
        System.out.println(Thread.currentThread().getName() + " trying to withdraw " +amount);

        double accBal = bankAcc.get(accId);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(accBal < amount) {   
            System.out.println("Insufficient balance ");
        } else {
            accBal = accBal - amount;
            bankAcc.put(accId, accBal);
            System.out.println("Balance after withdrawn: " +accBal);
        }
    }

    public double getAmount() {
        return amount;
    }
    
}
