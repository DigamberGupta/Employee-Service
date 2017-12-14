package com.digambergupta.employees.dao;

import com.digambergupta.employees.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeByEmployeeId(Long employeeId);

}
