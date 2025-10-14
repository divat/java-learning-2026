package com.diva.basics.collections.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingStringArrayDemo {

    public static void main(String[] args) {
        sortPlainString("bdca");

        List<Employee> empList = Arrays.asList(
            new Employee(1241, "Jahan", 3597.023),
            new Employee(6461, "Dhivakar", 3592427.353),
            new Employee(1241, "Ila", 3597.023)
        );

        Collections.sort(empList);
        empList.stream().forEach(e ->{
            System.out.println(e.getName()+","+e.getId());
        }); 

        System.out.println(Math.min(10, 5));

    }

    private static void sortPlainString(String string) {
        char[] chars = string.toCharArray();

        Arrays.sort(chars);

        String sorted = new StringBuilder(new String(chars)).reverse().toString();

        System.out.println("===Sorted Plain String=== :: " +sorted);
        
    }

    private static void sortStrings(String[] str) {
        Arrays.sort(str, Collections.reverseOrder());
        Collections.synchronizedList(new ArrayList<>())
    }
    
    
}

class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final double salary;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return o.getName().compareTo(this.getName());
    }


}
