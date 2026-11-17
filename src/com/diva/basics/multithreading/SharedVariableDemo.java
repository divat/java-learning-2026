package com.diva.basics.multithreading;

import java.util.HashMap;
import java.util.Map;

public class SharedVariableDemo {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException{

        BankAccount bank = new BankAccount();
        bank.deposit("D10001", 1000.00);

        Runnable withdrawTask = () -> {
            bank.withdraw("D10001", 300);
        };

        Runnable counterTask = () -> {
            for(int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count++;
                //incrementCount();
            }
        };

        Thread counterTask1 = new Thread(counterTask, "CounterTaskThread-1");
        Thread counterTask2 = new Thread(counterTask, "CounterTaskThread-2");

        counterTask1.start();
        counterTask2.start();
        counterTask1.join();counterTask2.join();

        System.out.println("Final counter :: " +count);

        Thread t1 = new Thread(withdrawTask, "Worker-1");
        Thread t2 = new Thread(withdrawTask, "Worker-2");

        t1.start(); t2.start();

    }

    private static void incrementCount() {
        count++;
    }

}

class BankAccount {

    private double amount;

    private final Map<String, Double> bankAcc = new HashMap<>();

    private double getBalance(String accountId) {
        return this.amount;
    }

    public void deposit(String accountId, double amount) {
        bankAcc.put(accountId, amount);
    }

    public synchronized void withdraw(String accId, double amount) {
        System.out.println(Thread.currentThread().getName() + " trying to withdraw " + amount);
       double accBal = bankAcc.get(accId);

         try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


       if(accBal < amount) {
            System.out.println("Insufficient balance.");
       }else{
            accBal = accBal - amount;
            bankAcc.put(accId, accBal);
            System.out.println("Balance after withdrawn: " +accBal);
       }
    }

}
