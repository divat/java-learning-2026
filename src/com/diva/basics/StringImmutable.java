package com.diva.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringImmutable {

    public static void main(String[] args) throws Exception {
        List<String> tasks = new ArrayList<>();
        tasks.add("T1");
        Employee e = new Employee(tasks);

        System.out.println(e.getTask());

        tasks.add("T3");

        System.out.println(e.getTask());

    }

}

final class Employee {

    private List<String> task;

    public Employee(List<String> tasks) {
        this.task = new ArrayList<>(tasks);
    }

    public List<String> getTask(){
        return task;
    }

}

class Address {

    private String city;

    Address(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }
    
}
