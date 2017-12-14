package com.digambergupta.employees.service.api;

import com.digambergupta.employees.resource.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Service Interface for EmployeeService
 * @author Digamber Gupta
 */
public interface EmployeeService {

    EmployeeDTO getEmployeeByEmployeeId(Long employeeId);

    EmployeeDTO setEmployeeDetails(EmployeeDTO employee);

    Page<EmployeeDTO> getEmployees(PageRequest pageRequest);
}
