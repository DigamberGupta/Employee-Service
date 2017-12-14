package com.digambergupta.employees.converter;

import com.digambergupta.employees.domain.Employee;
import com.digambergupta.employees.resource.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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
    public static Optional<EmployeeDTO> convertAndJoin(final Employee employee) {

        if (employee == null) {
            return Optional.empty();
        }

        final EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeFirstName(employee.getFirstName());
        employeeDTO.setEmployeeLastName(employee.getLastName());
        employeeDTO.setBirthday(employee.getBirthday());

        return Optional.of(employeeDTO);
    }

    /**
     * Convert the page resource to data model class
     *
     * @param employees employee list
     * @return EmployeeDTO
     */
    public static Optional<Page<EmployeeDTO>> covertAndJoinPage(final Page<Employee> employees) {

        if (CollectionUtils.isEmpty(employees.getContent()))
            return Optional.empty();

        List<Employee> employeeList = employees.getContent();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (final Employee employee : employeeList) {
            employeeDTOS.add(getEmployeeBuilder(employee));
        }

        final Pageable pageable = new PageRequest(employees.getNumber(), employees.getSize());

        return Optional.of(new PageImpl<>(employeeDTOS, pageable, employees.getTotalElements()));
    }

    /**
     * Builder class for EmployeeDTO
     *
     * @param employee employee object
     * @return EmployeeDTO
     */
    private static EmployeeDTO getEmployeeBuilder(final Employee employee) {
        final EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeFirstName(employee.getFirstName());
        employeeDTO.setEmployeeLastName(employee.getLastName());
        employeeDTO.setBirthday(employee.getBirthday());

        return employeeDTO;
    }
}
