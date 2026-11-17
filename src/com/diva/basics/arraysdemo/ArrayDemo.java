package com.diva.basics.arraysdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayDemo {

    public static void main(String[] args) {
        int[] num = {5,4,3,2,1};

        //System.out.println(Arrays.toString(revArr(num)));
        System.out.println(Arrays.toString(reverseArray(num)));
        System.err.println(Arrays.toString(num));

        //Reverse double array 
        double[] arr = {8.7,8.9,7.2,6.7,9.8,5.4,5.8};
        double[] reversedArray = reverseDoubleArray(arr.clone());
        System.out.println(Arrays.toString(reversedArray));

        //Apply descending order using stream
       System.out.println( Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).toList());

       //Remove elements from array and shrink the size
       removeElementsInArray();

       showRecentTransaction();
       findHigestAndSmallestTrans();
        calculateTotalMonthlySpend();
        calculateDailySteps();
        countProductPurchase();
    }

    private static void removeElementsInArray(){
        int[] nums = {1,2,3,4,5,6};
    
       int removeIndex = 3;

       int len = nums.length - 1;
       Integer[] newNumsArr =  new Integer[len];
       for(int i = 0, j = 0; i < len; i++) {
            if(i != removeIndex){
                newNumsArr[j++] = nums[i];
            }
       }

       for(Integer n : newNumsArr){
        System.out.println("===New Array after removal===" +n);
       }
    }

    private static double[] reverseDoubleArray(double[] arr) {
        
        int start = 0; int end = arr.length - 1;

        while(start < end) {
            double temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }

        return arr;

    }

    /**
     * Reverse given array
     * @param arr
     * @return
     */
    private static int[] reverseArray(int[] arr){
        int start = 0; int end = arr.length - 1;
        int[] result = arr.clone();
        for(int i = 0; i < end; i++) {
            int temp = result[i]; //5
            result[start] = result[end]; //5=1
            result[end] = temp; //1=5

            start++;
            end--;
        }
        return result;
    }

    private static int findMax(int[] arr) {
        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("Empty array");

        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static int findMin(int[] arr) {
        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("Empty array");

        int min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    //Upskill & refresh
    private static void showRecentTransaction() {
        int[] transactions = {100, 250, 50, 400, 75, 600, 200, 90, 300, 150};

        int start = 0; int end = transactions.length - 1;
        int[] transResult = transactions.clone();

        for(int i=0; i<transResult.length; i++){
            int temp = transactions[i];
            transResult[start] = transResult[end];
            transResult[end]=temp;

            start++;
            end--;
        }

        System.out.println("Show recent transactions :: " +Arrays.toString(transResult));

    }

    private static void findHigestAndSmallestTrans() {
        int[] expenses = {200, 450, 120, 800, 300};

        // Find max and min expense
        int max = expenses[0];
        for(int i=0;i<expenses.length;i++){
            if(expenses[i] > max) {
                max = expenses[i];
            }
        }
        System.out.println("Highest transaction :: " +max);

        int min = expenses[0];
        for(int i=0;i<expenses.length;i++){
            if(expenses[i] < min) {
                min = expenses[i];
            }
        }
        System.out.println("Smallest transaction :: " +min);
    }

    private static void calculateTotalMonthlySpend() {
        int[] monthlyExpenses = {100, 200, 150, 250, 300};

        int sum = 0;
        for(int i=0; i<monthlyExpenses.length; i++) {
            sum += monthlyExpenses[i];
        }
        System.out.println("Total monthly spend ::" +sum);
    }

    private static void calculateDailySteps() {
        int[] steps = {5000, 7000, 6500, 8000, 7200};

        int sum = 0;
        for(int i=0; i<steps.length; i++){
            sum += steps[i]; 
        }

        double avgSteps = sum/steps.length;
        System.out.println("Daily average steps :: " +avgSteps);
    }

    private static void countProductPurchase() {
        int[] purchases = {101, 102, 101, 103, 101, 102};
        int productId = 101;

        int count = 0;
        for(int i=0;i<purchases.length; i++) {
            if(purchases[i] == productId) {
                count++;
            }
        }
        System.out.println("Customer bought this product :: " +count);
    }

    private static void removeDuplicateEmails() {
        String[] emails = {"a@mail.com", "b@mail.com", "a@mail.com", "c@mail.com"};

        
    }

}
