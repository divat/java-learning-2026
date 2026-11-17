package com.diva.basics.java8.functionalprogramming;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        Manager managerData =
        new Manager(
                501L,
                "David",
                "david@company.com"
        );
       
        EmpOptional employee =
        new EmpOptional(
                101L,
                "Alice",
                managerData
        );
        
        Manager managerEmail = findManager(employee).orElseThrow(
            () -> new IllegalStateException("Employee doesn't have manager.s")
        );

        System.out.println("Manager Email :: " +managerEmail.getEmail());

    }

    private static Optional<String> getString() {
        return null;
    }

    private static Optional<Manager> findManager(EmpOptional emp) {
        return Optional.ofNullable(emp.getManager());
    }

}

class EmpOptional {
    private Long id;
    private String name;
    private Manager manager;

    public EmpOptional(Long id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }
}

class Manager {

    private Long id;
    private String name;
    private String email;

    public Manager(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
