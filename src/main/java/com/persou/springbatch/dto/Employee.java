package com.persou.springbatch.dto;

import java.time.LocalDateTime;
import java.util.Objects;


public class Employee {

    private Long id;

    private String employeeCode;

    private String document;

    private String fullName;

    private String department;

    private LocalDateTime createdAt;

    public Employee() {

    }

    public Employee(Long id, String employeeCode, String document, String fullName, String department,
                    LocalDateTime createdAt) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.document = document;
        this.fullName = fullName;
        this.department = department;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(employeeCode, employee.employeeCode) &&
            Objects.equals(document, employee.document) &&
            Objects.equals(fullName, employee.fullName) &&
            Objects.equals(department, employee.department) &&
            Objects.equals(createdAt, employee.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeCode, document, fullName, department, createdAt);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", employeeCode='" + employeeCode + '\'' +
            ", document='" + document + '\'' +
            ", fullName='" + fullName + '\'' +
            ", department='" + department + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}