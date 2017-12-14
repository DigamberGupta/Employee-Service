package com.digambergupta.employees.assembler;

import com.digambergupta.employees.controller.EmployeesController;
import com.digambergupta.employees.resource.EmployeeDTO;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Resource assembler class for Employee
 *
 * @author Digamber Gupta
 * @see AbstractResourceAssembler
 */
@Component
public class EmployeeResourceAssembler extends AbstractResourceAssembler<EmployeeDTO, EmployeeDTO> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     */
    public EmployeeResourceAssembler() {
        super(EmployeesController.class, EmployeeDTO.class);
    }


    /**
     * Allows to append HAL metadata, like self link, links to other resources, etc.
     *
     * @param entity {@link EmployeeDTO}
     * @return adjusted {@link EmployeeDTO}
     */
    public EmployeeDTO toResource(EmployeeDTO entity) {

        // Add self link
        entity.add(linkTo(ControllerLinkBuilder.methodOn(EmployeesController.class)
                .getEmployeeById(entity.getEmployeeId()))
                .withSelfRel());
        return entity;
    }
}
