package com.digambergupta.employees.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    protected Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, java.sql.Date birthday) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }
}
