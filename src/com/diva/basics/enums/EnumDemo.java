package com.diva.basics.enums;

public class EnumDemo {

    public static void main(String[] args) {
        for(AccountType accountType : AccountType.values()) {
            System.out.println(accountType);
        }

        System.out.println(AccountType.SAVINGS.isWithdrawAllowed(AccountType.LOAN));
        
    }

}

enum AccountType {
    SAVINGS(1000, "Savings Account"),
    CURRENT(2000, "Current Account"), 
    LOAN(0, "Loan Account");


    private final double minBal;
    private final String description;

    private AccountType(double minBalance, String description) {
        this.minBal = minBalance;
        this.description = description;
    }

    public boolean isWithdrawAllowed(AccountType accType) {
        return accType != LOAN;
    }

    public double getMinBal() {
        return minBal;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + " (" + description + ", Min Balance: ₹: " + minBal + ")";
    }

}
