package com.diva.basics.java8.functionalprogramming;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Java8CollectorsDemo {

    public static void main(String[] args) {
        System.out.println("Collectors toList() method :: " +collectorToList());
    }

    private static List<Employee> collectorToList() {
        return getEmployees().stream()
                .collect(Collectors.toList());
    }

    private static Set<String> collectorToSet() {
        return getEmployees().stream()
                .map(Employee::getDept)
                .collect(Collectors.toSet());
    }

    private static TreeSet<String> collectorsToCollection() {
        return getEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static Map<String, Double> collectorsToMap() {
        return getEmployees().stream()
                .collect(Collectors.toMap(
                    Employee::getDept, 
                    Employee::getSalary, 
                    (salary1, salary2) -> salary1 + salary2
                ));
    }

    private static Map<String, List<Employee>> collectorGroupingBy() {
        return getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDept));
    }

    private static Map<String, Long> collectorGroupingByDownstream() {
        return getEmployees().stream()
                .collect(Collectors.groupingBy(
                    Employee::getDept,
                    Collectors.counting()
                ));
    }

    private static Map<Boolean, List<Employee>> collectorPartitionBy() {
        return getEmployees().stream()
                .collect(Collectors.partitioningBy(
                    Employee::isActive
                ));
    }

    /**
     * Get the employees active and inactive count
     * @return
     */
    private static Map<Boolean, Long> collectorPartitionByDownstream() {
        return getEmployees().stream()
                .collect(Collectors.partitioningBy(
                    Employee::isActive,
                    Collectors.counting()
                ));
    }

    //Get the employees under each department
    private static Map<String, List<String>> getEmployeesForEachDept() {
        return getEmployees().stream()
                .collect(
                    Collectors.groupingBy(
                        Employee::getDept, 
                        Collectors.mapping(
                            Employee::getName, 
                            Collectors.toList() 
                        )
                    )
                );
    }

    //Sum all employee salaries
    private static DoubleSummaryStatistics sumEmployeeSalary() {
        return getEmployees().stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
    }

    private static List<Employee> getEmployees() {
        return List.of(
             new Employee(1, "Ava", "IT", 80_000, true),
            new Employee(2, "Ben", "HR", 50_000, true),
            new Employee(3, "Chris", "IT", 95_000, false),
            new Employee(4, "Dana", "FIN", 1_20_000, true),
            new Employee(5, "Evan", "IT", 60_000, true)
        );
    }

}

@Data
@AllArgsConstructor
class Employee {
    private int empId;
    private String name;
    private String dept;
    private double salary;
    private boolean active;
}
