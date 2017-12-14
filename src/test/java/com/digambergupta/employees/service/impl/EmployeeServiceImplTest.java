package com.digambergupta.employees.service.impl;

import com.digambergupta.employees.dao.EmployeeRepository;
import com.digambergupta.employees.domain.Employee;
import com.digambergupta.employees.resource.EmployeeDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Test for EmployeeServiceImpl
 *
 * @author Digamber Gupta
 * @see EmployeeServiceImpl
 */
public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        this.employeeRepository = Mockito.mock(EmployeeRepository.class);
        this.employeeService = new EmployeeServiceImpl(this.employeeRepository);
    }

    @Test
    public void getEmployeeByEmployeeId() throws Exception {
        Employee employee = new Employee(1L, "Raj", "Gupta", Date.valueOf("2017-12-01"));

        when(this.employeeRepository.findEmployeeByEmployeeId(Mockito.anyLong())).thenReturn(employee);

        EmployeeDTO employeeDTO = this.employeeService.getEmployeeByEmployeeId(1L);

        Assert.assertNotNull(employeeDTO);
        Assert.assertEquals(1L, employeeDTO.getEmployeeId().longValue());

        verify(this.employeeRepository, times(1)).findEmployeeByEmployeeId(1L);
        verifyNoMoreInteractions(this.employeeRepository);

    }

    @Test
    public void setEmployeeDetails() throws Exception {
        Pageable pageable = new PageRequest(0, 10);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "Raj", "Gupta", Date.valueOf("2017-12-01")));
        employeeList.add(new Employee(2L, "Chaitanya", "sable", Date.valueOf("2015-11-01")));

        Page<Employee> employeePage = new PageImpl<>(employeeList, pageable, 2);
        when(this.employeeRepository.findAll(pageable)).thenReturn(employeePage);

        Page<EmployeeDTO> employeeDTOPage = this.employeeService.getEmployees(new PageRequest(0, 10));

        Assert.assertNotNull(employeeDTOPage);
        Assert.assertEquals(2, employeeDTOPage.getTotalElements());
        Assert.assertEquals("Raj", employeeDTOPage.getContent().get(0).getEmployeeFirstName());
        verify(this.employeeRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(this.employeeRepository);
    }

}