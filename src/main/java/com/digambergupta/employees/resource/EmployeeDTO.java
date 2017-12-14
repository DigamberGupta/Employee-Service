package com.digambergupta.employees.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;

/**
 * Model class for Employee Data
 *
 * @author Digamber Gupta
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDTO extends ResourceSupport{

    private Long employeeId;

    private String employeeFirstName;

    private String employeeLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
