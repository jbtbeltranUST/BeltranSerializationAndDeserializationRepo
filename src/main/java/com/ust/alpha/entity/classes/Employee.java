package com.ust.alpha.entity.classes;

import java.io.Serializable;

public class Employee implements Serializable {
    public String name;
    public String address;
    public String role;

    public Employee(String name, String address, String role) {
        this.name = name;
        this.address = address;
        this.role = role;
    }

    public String toString() {
        return "Employee{name='" + this.name + "', address='" + this.address + "', role='" + this.role + "'}";
    }
}