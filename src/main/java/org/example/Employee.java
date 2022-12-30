package org.example;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Employee implements Cloneable {
    int id;
    String name;
    int age;
    String gender;
    String department;
    int yearOfJoining;
    double salary;
}

class Test2
{
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        //number of male and female employees count
        Map<String, Long> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(collect);

        //print the names of all department names in the organization
        Stream<String> stringStream = employeeList.stream().map(Employee::getDepartment).distinct();
        stringStream.forEach(System.out::println);

        //print average age of male and female employees
        Map<String, Double> collect1 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(collect1);
        
        //get the details of highest paid employee in the organization
        Optional<Double> max = employeeList.stream().map(e -> e.getSalary()).max((e1, e2) -> e1 > e2 ? 1 : -1);
        System.out.println(max);

        Employee employee = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(employee);

        //get the details of lowest paid employee in the organization
        employeeList.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(employee);

        //Get the names of all employees who have joined after 2015?

        List<Employee> empJoinedAfter2015 = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).collect(Collectors.toList());
        System.out.println(empJoinedAfter2015);

        employeeList.stream().filter(e -> e.getYearOfJoining()>2015).map(Employee::getName).forEach(System.out::println);

        //Count the number of employees in each department?
        Map<String, Long> collect2 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        Set<Map.Entry<String, Long>> entries = collect2.entrySet();
        for(Map.Entry<String,Long> entry : entries)
        {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        //What is the average salary of each department?
        Map<String, Double> collect3 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        Set<Map.Entry<String, Double>> entries1 = collect3.entrySet();
        for(Map.Entry<String,Double> entry: entries1)
        {
            System.out.println("Department name: "+entry.getKey()+"- Salary:"+entry.getValue());
        }

        //Get the details of the youngest male employee in the product development department?
        Optional<Employee> min = employeeList.stream().filter(e -> e.getDepartment() == "Product Development" && e.getGender() == "Male").min(Comparator.comparingInt(Employee::getAge));
        System.out.println(min.get());

        //Who has the most working experience in the organization?
        Optional<Employee> min1 = employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining));
        System.out.println(min1.get());

        //How many male and female employees are there in the sales and marketing team?
        Map<String, Long> collect4 = employeeList.stream().filter(e -> e.getDepartment() == "Sales And Marketing").collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        Set<Map.Entry<String, Long>> entries2 = collect4.entrySet();
        for(Map.Entry<String,Long> entry2 : entries2)
        {
            System.out.println("gender: "+entry2.getKey()+", count :"+entry2.getValue());
        }

        //What is the average salary of male and female employees?
        Map<String, Double> collect5 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salary :"+collect5.toString());

        //List down the names of all employees in each department?
        Map<String, List<Employee>> collect6 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Set<Map.Entry<String, List<Employee>>> entries3 = collect6.entrySet();
        for(Map.Entry<String,List<Employee>> entry: entries3)
        {
            System.out.println("department "+entry.getKey());
                    for(Employee e : entry.getValue())
                    {
                        System.out.println("employee name : "+e.getName());
                    }
        }


        //What is the average salary and total salary of the whole organization?
        Double totalSalary = employeeList.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("Total salary in organization: "+totalSalary);

        Double avgSalary = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("Average salary in organization: "+avgSalary);

        DoubleSummaryStatistics salarySummary = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(salarySummary); //we can extract count, min, avg, max from here example : salarySummary.getSum()

        //Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Set<Map.Entry<Boolean, List<Employee>>> collect7 = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25)).entrySet();

        for(Map.Entry<Boolean,List<Employee>> entry: collect7)
        {
         if(entry.getKey())
         {
             System.out.println("employees above 25 age :");
         }
         else
         {
             System.out.println("employees below 25 age :");
         }

            System.out.println("---------------------------------");
            List<Employee> list = entry.getValue();
            for(Employee e : list)
            {
                System.out.println(e.getName());
            }
        }


        //Who is the oldest employee in the organization? What is his age and which department he belongs to?
        Optional<Employee> max1 = employeeList.stream().max((e1, e2) -> e1.getAge() > e2.getAge() ? 1 : -1);
        System.out.println(max1.get());

        Optional<Employee> collect8 = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println(collect8);


    }
}
