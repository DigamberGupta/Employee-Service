package com.digambergupta.employees.controller;

import com.digambergupta.employees.assembler.EmployeeResourceAssembler;
import com.digambergupta.employees.resource.EmployeeDTO;
import com.digambergupta.employees.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Controller class for the Employee details
 *
 * @author Digamber Gupta
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeeService employeeService;

    private EmployeeResourceAssembler employeeResourceAssembler;

    @Autowired
    public EmployeesController(final EmployeeService employeeService, final EmployeeResourceAssembler employeeResourceAssembler) {
        this.employeeService = employeeService;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    /**
     * get the Employee details by employeeId
     *
     * @param employeeId employeeId
     * @return Employee
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<EmployeeDTO> getEmployeeById(@RequestParam(name = "employeeId") Long employeeId) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmployeeId(employeeId);
        employeeDTO.add(linkTo(ControllerLinkBuilder.methodOn(EmployeesController.class)
                .getEmployeeById(employeeId)).withSelfRel());

        return ResponseEntity.ok(employeeDTO);
    }

    /**
     * Create the new Employee
     *
     * @param employee employee
     * @return EmployeeDTO
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<EmployeeDTO> setEmployeeDetails(@RequestBody EmployeeDTO employee) {
        EmployeeDTO employeeDTO = employeeService.setEmployeeDetails(employee);
        employeeDTO.add(linkTo(ControllerLinkBuilder.methodOn(EmployeesController.class)
                .setEmployeeDetails(employee)).withSelfRel());
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    /**
     * Get the list Employees
     *
     * @param page                    page number
     * @param size                    page size
     * @param pagedResourcesAssembler pageResource Assembler
     * @return PageResources of EmployeeDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<PagedResources<EmployeeDTO>> getAllEmployees(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            final PagedResourcesAssembler<EmployeeDTO> pagedResourcesAssembler) {

        Page<EmployeeDTO> employeeDTOPage = this.employeeService.getEmployees(new PageRequest(page, size));

        return new ResponseEntity<>(
                pagedResourcesAssembler.toResource(employeeDTOPage, this.employeeResourceAssembler,
                        linkTo(ControllerLinkBuilder.methodOn(EmployeesController.class)
                                .getAllEmployees(page, size, pagedResourcesAssembler)).withRel("self")),
                HttpStatus.OK);
    }
}
