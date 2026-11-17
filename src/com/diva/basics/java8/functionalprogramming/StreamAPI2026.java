package com.diva.basics.java8.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPI2026 {

    public static void main(String[] args) {
        
        //Find highest-paid employee in each department.
        List<Employ> empList = getEmpList();

        Map<String, Optional<Employ>> highestPaidEmpByDept = empList.stream()
            .collect(Collectors.groupingBy(
                employ -> employ.getDeptName(), 
                Collectors.maxBy(
                    Comparator.comparingDouble(
                        employ -> employ.getSalary()
                    )
                ))
            );

        
        highestPaidEmpByDept.forEach(
            (dept, employ) -> {
                System.out.println("Department :: " +dept + "-" +"["+employ.get().getName()+"-" +employ.get().getSalary() + "]");
            }
        );

        Double totalSalary = empList.stream()
                                .collect(
                                    Collectors.summingDouble(
                                        Employ::getSalary
                                    )
                                );
        System.out.println("Total Salary :: " +totalSalary);

        DoubleSummaryStatistics statistics = empList.stream()
                                                .collect(
                                                    Collectors.summarizingDouble(
                                                        Employ::getSalary
                                                    )
                                                );
        System.out.println("Employee Statistics :: " +statistics.getMax());

        Map<String, DoubleSummaryStatistics> e = empList.stream().collect(Collectors.groupingBy(
            Employ::getDeptName,
            Collectors.summarizingDouble(Employ::getSalary)
       ));
       System.out.println(e);

       Optional<Employ> emp = empList.stream().max(Comparator.comparing(Employ::getSalary));
       System.out.println(emp.get().getName());

      // Map<String, EmployResponse> resp = getHighestPaidEmpResponse(empList);
    
      double saa =  empList.stream().mapToDouble(Employ::getSalary).max().orElse(0.0);

       System.out.println(saa); 

       //Primitives with streams
       List<Integer> scores = Arrays.asList(10 ,20,30,40,50);
       int sum = scores.stream().mapToInt(Integer::intValue).sum(); //average(), max(), min(), summaryStatistics()
       System.out.println("Primitive sum :: " +sum);

       int[] nums = {1,2,3,4,5};
       List<Integer> numbers = //IntStream.rangeClosed(1, 5)
            IntStream.of(nums)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        System.out.println(numbers);

        //Nth highest salary
      Optional<Long> nthSalary =  getNthHighestSalary(empList, 2);
      if(nthSalary.isPresent()){
            System.out.println(nthSalary);
      }

      

    }   

    private static Optional<Long> getNthHighestSalary(List<Employ> salaries, int n) {
        if(n <= 0 || salaries.isEmpty()) {
            return Optional.empty();
        }

        long[] sals =  salaries.stream().sorted(Comparator.comparing(Employ::getSalary).reversed())
                            .mapToLong(Employ::getSalary)
                            .toArray();

        System.out.println("Sorted salaries :: " + Arrays.toString(sals));

        return salaries.stream()
                    .map(Employ::getSalary)
                    .distinct()
                    .sorted(Comparator.reverseOrder())
                    .skip(n - 1)
                    .findFirst();        
    }

    //Highest paid employee in each department and return it as employee response
    private static Map<String, EmployResponse> getHighestPaidEmpResponse(List<Employ> emplList) {
        return  emplList.stream()
                    .collect(Collectors.groupingBy(
                        Employ::getDeptName,
                        Collectors.collectingAndThen(
                            Collectors.maxBy(
                                Comparator.comparing(Employ::getSalary)
                            ), 
                            optionalEmp -> optionalEmp
                                    .map(empl -> new EmployResponse(
                                        empl.getId(), 
                                        empl.getName()
                                    ))
                                    .orElse(null)
                        )
                    ));
    }
    
    private static List<Employ> getEmpList() {
        List<Employ> empList = new ArrayList<>();
            empList.add(new Employ(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
            empList.add(new Employ(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
            empList.add(new Employ(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
            empList.add(new Employ(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));

            empList.add(new Employ(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
            empList.add(new Employ(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
            empList.add(new Employ(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
            empList.add(new Employ(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
            empList.add(new Employ(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));
        return empList;
    }
}

class EmployResponse {
    private Long id;
    private String name;

    public EmployResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}

class Employ {
    private int id;
    private String name;
    private int age;
    private long salary;
    private String gender;
    private String deptName;
    private String city;
    private int yearOfJoining;

    public Employ(int id, String name, int age, long salary, String gender, String deptName, String city,
            int yearOfJoining) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.deptName = deptName;
        this.city = city;
        this.yearOfJoining = yearOfJoining;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getYearOfJoining() {
        return yearOfJoining;
    }
    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    
}
