package com.digambergupta.employees.service.api;

import com.digambergupta.employees.resource.EmployeeDTO;

/**
 * Service Interface for EmployeeService
 * @author Digamber Gupta
 */
public interface EmployeeService {

    EmployeeDTO getEmployeeByEmployeeId(Long employeeId);
}
