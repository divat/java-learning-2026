package com.diva.basics.java8.functionalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalInterfacesDemo {

    public static void main(String[] args) {

        List<User> userList = Arrays.asList(
            new User(101, "Dhivakar", 49860.935, Arrays.asList("aaa@dev.com", "efdf@dev.com")),
            new User(102, "Jahan", 6461435.255, Arrays.asList("wsf@dev.com", "ddfder@dev.com")),
            new User(101, "Ila", 234164.643, Arrays.asList("dfefw@dev.com", "hkhk@eev.com"))
        );

        new Consumer<User>() {
            @Override
            public void accept(User t) {
              
            }
        };

        Predicate<User> userNameLength = u -> u.getName().length() > 6;

        findUser(userList, userNameLength);

        //flatMap
        List<String> emailList = userList.stream()
            .flatMap(user -> user.getEmails().stream().map(e -> e.toUpperCase()))
            .map(email -> email.split("@")[1])
            .toList();

        System.out.println("Email List :: " +emailList);    
    }

    private static void findUser(List<User> users, Predicate<User> predicate) {
        users.forEach(u -> {
            if(predicate.test(u)){
                System.out.println("User is valid :: " +u.getName());
            }
        });
    }

}

class User {

    private int id;
    private String name;
    private double salary;
    private List<String> emails;

    public User(int id, String name, double salary, List<String> emails) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.emails = emails;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public List<String> getEmails() {
        return emails;
    }
    public void setEmails(List<String> email) {
        this.emails = email;
    }
    
}