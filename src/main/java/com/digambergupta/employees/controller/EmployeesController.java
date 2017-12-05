package com.digambergupta.employees.controller;

import com.digambergupta.employees.resource.EmployeeDTO;
import com.digambergupta.employees.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for the Employee details
 *
 * @author Digamber Gupta
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * get the Employee details by employeeId
     *
     * @param employeeId employeeId
     * @return Employee
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<EmployeeDTO> getEmployeeById(@RequestParam(name = "employeeId", required = true) Long employeeId) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmployeeId(employeeId);

        return ResponseEntity.ok(employeeDTO);
    }
}
