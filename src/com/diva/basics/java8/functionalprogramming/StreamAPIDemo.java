package com.diva.basics.java8.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import javax.swing.GroupLayout.Group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class StreamAPIDemo {

    public static void main(String[] args) {

        //Group the employees by city
        Map<String, List<Employee>> employeesByCity = getEmpList().stream()
                .collect(Collectors.groupingBy(
                        Employee::getCity)
                );
        
        System.out.println("Group Employees By City :: " +employeesByCity);

        //Find the count of male and female employees present in the organization.
        Map<String, Long> countEmployeesByGender = getEmpList().stream()
                .collect(Collectors.groupingBy(
                    Employee::getGender,
                    Collectors.counting()
                ));

        System.out.println("Count Employees By Gender :: " +countEmployeesByGender);

        //Find the count of male and female present in each department
        Map<String, Map<String, Long>> countEmpGenderByDept = getEmpList().stream()
                .collect(Collectors.groupingBy(
                    e -> e.getDeptName(),
                    Collectors.groupingBy(Employee::getGender,Collectors.counting())
                ));
            
        System.out.println("Count of Male & Female in each dept :: " +countEmpGenderByDept);

        //Print the names of all distinct departments in the organization.
        List<String> depList = getEmpList().stream().map(Employee::getDeptName).distinct().toList();
        System.out.println(depList);

        //Print employee details whose age is greater than 28 in the organisation.
        Predicate<Employee> isAgeGT28 = e -> e.getAge() > 28;
        getEmpList().stream().filter(isAgeGT28).forEach(e -> {
            System.out.println("Emp name :: " +e.getName());
        });

        //Find maximum age/oldest of employee in the organisation.
        Optional<Employee> oldestEmp = getEmpList().stream().max(Comparator.comparing(Employee::getAge));
        oldestEmp.ifPresent(e -> {
            System.out.println("Oldest employee :: " +e.getName());
        });

        //Print Average age of Male and Female Employees in the organisation.
        Map<String, Double> avgAge = getEmpList().stream().collect(Collectors.groupingBy
                             (Employee::getGender,
                              Collectors.averagingInt(Employee::getAge)
                             ));
        System.out.println("Average age of Male and Female Employees:: " + avgAge);


        //Print Average age of Male and Female Employees in each department.
        Map<String,Map<String, Double>> averageAge = getEmpList().stream()
                    .collect(Collectors.groupingBy(
                        Employee::getDeptName,
                        Collectors.groupingBy(
                            Employee::getGender,
                            Collectors.averagingInt(Employee::getAge)
                        )
                    ));

        averageAge.forEach((dept, genderMap) -> {
            genderMap.forEach((gender, age) -> {
                System.out.println("Dept name :: " +dept+" Gender :: " +gender+" Age :: " +age);
            });
        });

        int[] arr = {1,2,3,5,6};

        BinaryOperator<Integer> sum = (a,b) -> a * b;
        Arrays.stream(arr).boxed().reduce(sum);

       long totalSal = getEmpList().stream().collect(Collectors.reducing(0L, Employee::getSalary, Long::sum));

       System.out.println(totalSal);

       Map<String, DoubleSummaryStatistics> e = getEmpList().stream().collect(Collectors.groupingBy(
            Employee::getDeptName,
            Collectors.summarizingDouble(Employee::getSalary)
       ));
       System.out.println(e);
       
                
    }

    private static List<Employee> getEmpList() {
        List<Employee> empList = new ArrayList<>();
            empList.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
            empList.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
            empList.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
            empList.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));

            empList.add(new Employee(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
            empList.add(new Employee(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
            empList.add(new Employee(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
            empList.add(new Employee(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
            empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));
        return empList;
    }
}

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
class Employee {
    private int id;
    private String name;
    private int age;
    private long salary;
    private String gender;
    private String deptName;
    private String city;
    private int yearOfJoining;
}