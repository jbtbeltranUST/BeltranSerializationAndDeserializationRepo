package com.ust.alpha.entity;

import com.ust.alpha.entity.classes.Employee;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Serialization {
    public static void main(String[] args) {
        List<Employee> employeeList = getEmployees();
        serializeAll(employeeList);
        sortThenSerialize(employeeList);
    }

    private static List<Employee> getEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Monica Mittal", "Hyderabad", "Manager"));
        employeeList.add(new Employee("James", "Quezon City", "Lead"));
        employeeList.add(new Employee("Bryan", "North Caloocan", "Developer"));
        employeeList.add( new Employee("Edlyn", "Valenzuela", "Tester"));
        employeeList.add( new Employee("Hazel", "North Caloocan", "Lead"));
        employeeList.add( new Employee("Jhoewen", "North Caloocan" , "Developer"));
        employeeList.add( new Employee("Serina", "Tokyo", "Tester"));
        employeeList.add( new Employee("Raymond", "Bulacan", "Tester"));
        employeeList.add( new Employee("Azi", "Quezon City", "Developer"));
        return employeeList;
    }

    private static void serializeAll(List<Employee> employeeList) {
        try {
            fileOut("Employee", employeeList);
            System.out.print("Serialized data is saved in Employee file");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void sortThenSerialize(Collection<Employee> employeeList) {
        try {
            List<Employee> employeeManager = employeeList.stream()
                    .filter(e -> e.role.equals("Manager"))
                    .collect(Collectors.toList());
            List<Employee> employeeLeads = employeeList.stream()
                    .filter(e -> e.role.equals("Lead"))
                    .collect(Collectors.toList());
            List<Employee> employeeDeveloper = employeeList.stream()
                    .filter(e -> e.role.equals("Developer"))
                    .collect(Collectors.toList());
            List<Employee> employeeTester = employeeList.stream()
                    .filter(e -> e.role.equals("Tester"))
                    .collect(Collectors.toList());
            fileOut("Manager.txt", employeeManager);
            fileOut("Lead.txt", employeeLeads);
            fileOut("Tester.txt", employeeTester);
            fileOut("Developer.txt", employeeDeveloper);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void fileOut(String text, List<Employee> employeeList) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(text);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(employeeList);
        out.close();
        fileOut.close();
    }
}
