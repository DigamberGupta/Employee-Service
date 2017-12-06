package com.digambergupta.employees.converter;

import com.digambergupta.employees.domain.Employee;
import com.digambergupta.employees.resource.EmployeeDTO;

import java.util.Optional;

/**
 * Data converter class which convert the employee data to employee DTO data
 *
 * @author Digamber Gupta
 */
public class EmployeeDataConverter {

    private EmployeeDataConverter() {

    }

    /**
     * convert and Join method for employee data
     *
     * @param employee employee
     * @return optional of EmployeeDTO
     */
    public static Optional<EmployeeDTO> convertAndJoin(Employee employee) {

        if (employee == null) {
            return Optional.empty();
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeFirstName(employee.getFirstName());
        employeeDTO.setEmployeeLastName(employee.getLastName());
        employeeDTO.setBirthday(employee.getBirthday());

        return Optional.of(employeeDTO);
    }
}
