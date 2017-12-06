package com.digambergupta.employees.service.impl;

import com.digambergupta.employees.converter.EmployeeDataConverter;
import com.digambergupta.employees.dao.EmployeeRepository;
import com.digambergupta.employees.domain.Employee;
import com.digambergupta.employees.exception.ResourceNotFoundException;
import com.digambergupta.employees.resource.EmployeeDTO;
import com.digambergupta.employees.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
                employeeRepository.findEmployeeByEmployeeId(employeeId)).orElseThrow(() ->
                new ResourceNotFoundException("employeeId", employeeId));
    }
}
