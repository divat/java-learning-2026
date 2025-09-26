package com.diva.basics;

public class InvoiceTotalTaxCalc {
    static double[] prices = {250.0, 140.9, 1533.23, 3423.93};
    public static void main(String[] args) {
       System.out.println(  calculateTotalWithTax(prices));
    }

    private static double calculateTotalWithTax(double[] prices){
        double total = 0;
        double taxRate = 0.18;

         for(int i = 0; i < prices.length; i++) {
            total = total + prices[i];
         }

         double tax = total * taxRate;
         total = total + tax;
         return total;
        
    }

    
}
