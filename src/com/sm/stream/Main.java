package com.sm.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>();
        
		list.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		list.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		list.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		list.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		list.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		list.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		list.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		list.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		list.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		list.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		list.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		list.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		list.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		list.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		list.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		list.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		list.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		
		/* How many male and female employees are there in the organization? */
		
		list.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()))
		.forEach((gen,emp)->System.out.println(gen+"->"+emp));
		System.out.println("===================================");
		
		/* Print the name of all departments in the organization? */
		
		list.stream().map(Employee::getDepartment).collect(Collectors.toSet()).forEach(dept -> System.out.println(dept+".."));
		System.out.println("===================================");
	
		/* print the number of employees in each department */
		
		list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting())).forEach((dept,count)->System.out.println(dept+" -> "+count));
		System.out.println("===================================");
		
		/* What is the average age of male and female employees? */
		
		list.stream()
		.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)))
		.forEach((gen,ageAvg)->System.out.println(gen+" ->  "+ageAvg));
		System.out.println("===================================");
		
		/* Get the details of highest paid employee in the organization? */
		
		Optional<Employee> collect = list.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		System.out.println(collect);
		System.out.println("===================================");
		
		/* Get the names of all employees who have joined after 2015? */
		
		list.stream().filter(emp -> emp.getYearOfJoining()>2015).map(Employee::getName).forEach(System.out::println);
		System.out.println("===================================");
		
		/* What is the average salary of each department? */
		
		Map<String, Double> collect2 = list.stream()
		.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
		System.out.println(collect2);
		System.out.println("===================================");
		
		/*
		 * Get the details of youngest male employee in the product development
		 * department
		 */
		list.stream()
		.filter(emp -> emp.getDepartment().equals("Product Development"))
		.filter(emp -> emp.getGender().equals("Male"))
		.min(Comparator.comparingInt(Employee::getAge)).ifPresent(System.out::println);
		System.out.println("===================================");
		System.out.println("===================================");
		
		/* Who has the most working experience in the organization? */
		
		/*
		 * How many male and female employees are there in the sales and marketing team?
		 */
		
		list.stream()
		.filter(emp->emp.getDepartment().equals("Sales And Marketing"))
		.collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()))
		.forEach((gen,count)->System.out.println(gen+" -> "+count));
		System.out.println("===================================");
		
		/* sort the employee by their salary in decending order using col */
		
		/*
		 * Collections.sort(list,Comparator.comparing(Employee::getSalary).reversed());
		 * list.forEach(System.out::println);
		 */
		
		
		
		list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(System.out::println);
		System.out.println("===================================");
		
		/*Find max salary employee from each department*/
		list.stream()
		.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))))
		.forEach((key,value) -> System.out.println(key+" -> "+value));
		System.out.println("===================================");
		
		/* What is the average salary of male and female employees? */
		
		 
		list.stream()
		.collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)))
		.forEach((gender, avgSalary) ->
		System.out.println(gender + " : " + avgSalary));
	
		System.out.println("===================================");
	        
		/* List down the names of all employees in each department? */
	                
		list.stream()
		.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName, Collectors.toList())))
		.forEach((dept, names) ->System.out.println(dept + " : " + names));
		
		System.out.println("===================================");
		
		/*Who is the oldest employee in the organization? What is his age and which department he belongs to?*/
		
		Optional<Employee> emp = list.stream().max(Comparator.comparingInt(Employee::getAge));

		if (emp.isPresent()) {
		    Employee e = emp.get();
		    System.out.println("Oldest Employee Name : " + e.getName());
		    System.out.println("Age                  : " + e.getAge());
		    System.out.println("Department           : " + e.getDepartment());
		}
		
		System.out.println("===================================");
	
	}

}
