package com.digambergupta.employees.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * Model class for Employee Data
 *
 * @author Digamber Gupta
 */
@Data
public class EmployeeDTO {

    private Long employeeId;

    private String employeeFirstName;

    private String employeeLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
