package com.digambergupta.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * EmployeeApplication is the service to create or retrieve the information for the
 * employee
 * @author Digamber Gupta
 */
@SpringBootApplication
@EnableJpaRepositories
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }
}
