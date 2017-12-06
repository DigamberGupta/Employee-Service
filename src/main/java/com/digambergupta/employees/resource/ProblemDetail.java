package com.digambergupta.employees.resource;

import lombok.Data;

import java.util.List;

/**
 * Modal class for problem detail statement
 *
 * @author Digamber Gupta
 */
@Data
public class ProblemDetail {

    private final String title;

    private final String detail;

    private String type;

    private String instance;

    private Integer status;

    private List<ProblemDetail> errors;
}
