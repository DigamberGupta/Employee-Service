package com.digambergupta.employees.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    protected Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
