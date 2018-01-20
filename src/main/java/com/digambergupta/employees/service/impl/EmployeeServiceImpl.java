package com.digambergupta.employees.service.impl;

import com.digambergupta.employees.converter.EmployeeDataConverter;
import com.digambergupta.employees.dao.EmployeeRepository;
import com.digambergupta.employees.domain.Employee;
import com.digambergupta.employees.exception.ResourceNotFoundException;
import com.digambergupta.employees.resource.EmployeeDTO;
import com.digambergupta.employees.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for {@link EmployeeService}
 *
 * @author Digamber Gupta
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * get the Employee data by using employeeId
     *
     * @param employeeId employeeId
     * @return employeeDTo object
     */
    @Override
    public EmployeeDTO getEmployeeByEmployeeId(Long employeeId) {

        return EmployeeDataConverter.convertAndJoin(
                employeeRepository.findEmployeeByEmployeeId(employeeId))
                .orElseThrow(() -> new ResourceNotFoundException("employeeId", employeeId));
    }

    /**
     * create the Employee
     *
     * @param employeeDTO employeeDTO
     * @return EmployeeDTO
     */
    public EmployeeDTO setEmployeeDetails(final EmployeeDTO employeeDTO) {

        final Employee employee = new Employee(employeeDTO.getEmployeeId(), employeeDTO.getEmployeeFirstName(),
                employeeDTO.getEmployeeLastName(), employeeDTO.getBirthday());

        final Employee employeeDetail = employeeRepository.save(employee);

        return EmployeeDataConverter.convertAndJoin(employeeDetail)
                .orElseThrow(() -> new ResourceNotFoundException("employee", employeeDTO));
    }

    /**
     * Get the list employees
     *
     * @param pageRequest page and size
     * @return EmployeeDTO pageResource
     */
    public Page<EmployeeDTO> getEmployees(final PageRequest pageRequest) {
        Page<Employee> employees = employeeRepository.findAll(pageRequest);


        return EmployeeDataConverter.covertAndJoinPage(employees)
                .orElseThrow(() -> new ResourceNotFoundException("employees", "employees"));
    }
}
