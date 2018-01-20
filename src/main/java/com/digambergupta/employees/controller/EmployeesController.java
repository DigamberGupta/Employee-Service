package com.digambergupta.employees.controller;

import com.digambergupta.employees.assembler.EmployeeResourceAssembler;
import com.digambergupta.employees.resource.EmployeeDTO;
import com.digambergupta.employees.resource.ProblemDetail;
import com.digambergupta.employees.service.api.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Controller class for the Employee details
 *
 * @author Digamber Gupta
 */
@RestController
@RequestMapping("/employees")
@ExposesResourceFor(EmployeeDTO.class)
@Api(value = "Employees", description = "Employee API")
public class EmployeesController {

    private EmployeeService employeeService;

    private EmployeeResourceAssembler employeeResourceAssembler;

    @Autowired
    public EmployeesController(final EmployeeService employeeService,
                               final EmployeeResourceAssembler employeeResourceAssembler) {
        this.employeeService = employeeService;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    /**
     * get the Employee details by employeeId
     *
     * @param employeeId employeeId
     * @return Employee
     */
    @ApiOperation(
            value = "Retrieve a Employee resource by identifier",
            notes = "Make a GET request to retrieve a Employee detail by a given identifier",
            response = EmployeeDTO.class,
            httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved", response = EmployeeDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ProblemDetail.class),
            @ApiResponse(code = 403, message = "This operation is forbidden for this user",
                    response = ProblemDetail.class),
            @ApiResponse(code = 404, message = "Employee with the given identifier is not found",
                    response = ProblemDetail.class),
            @ApiResponse(code = 500, message = "Unexpected Internal Error", response = ProblemDetail.class)})
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
    @ApiOperation(
            value = "Create a Employee",
            notes = "Make a POST request to create a new Employee",
            response = EmployeeDTO.class,
            httpMethod = "POST")
    @RequestMapping(method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<EmployeeDTO> setEmployeeDetails(@Validated @RequestBody EmployeeDTO employee) {
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
    @ApiOperation(
            value = "Retrieve a List of Employee resource",
            notes = "Make a GET request to retrieve a List of Employee detail",
            response = EmployeeDTO.class,
            httpMethod = "GET")
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
