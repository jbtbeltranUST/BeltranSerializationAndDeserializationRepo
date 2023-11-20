package com.ust.alpha.entity;

import com.ust.alpha.entity.classes.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Deserialization {
    public static void main(String[] args) throws IOException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        displayEmployeesFromSingleFile(employeeList); // Displays all employees
        displayEmployeeFromFiles(); // displays with the roles of developer
    }

    private static void displayEmployees (List<Employee> employees) {
        System.out.println(employees);
    }

    private static void displayEmployeesFromSingleFile (Collection<Employee> employeeList) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream("Employee");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employeeList = (ArrayList<Employee>) in.readObject();
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Deserializing Employee...");
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
            System.out.println("Managers:");
            displayEmployees(employeeManager);
            System.out.println("Leads:");
            displayEmployees(employeeLeads);
            System.out.println("Developers:");
            displayEmployees(employeeDeveloper);
            System.out.println("Testers:");
            displayEmployees(employeeTester);
        }
    }

    private static void displayEmployeeFromFiles() throws IOException {
        String employeeRole = "Developer";
        try {
            FileInputStream fileIn = new FileInputStream(employeeRole + ".txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList newEmployeeList = (ArrayList) in.readObject();
            System.out.println(employeeRole + " contains: ");
            displayEmployees(newEmployeeList);
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
