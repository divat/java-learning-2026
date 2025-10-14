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

public class ReverseArray {

    public static void main(String[] args) {
        int[] num = {5,4,3,2,1};

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
            int temp = result[i];
            result[start] = result[end];
            result[end] = temp;

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


}
