package com.digambergupta.employees.adivice;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * A common Exception advice class for custom {@link ControllerAdvice}
 *
 * @author Digamber Gupta
 */
public class ExceptionAdvice {
    private static final String APPLICATION_PROBLEM_JSON = "application/problem+json";

    HttpHeaders overrideContentType() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", APPLICATION_PROBLEM_JSON);
        return httpHeaders;
    }

    String wrapWithFieldName(final ObjectError objectError) {
        String defaultMessage = objectError.getDefaultMessage();
        if (objectError instanceof FieldError) {
            defaultMessage = ((FieldError) objectError).getField() + " " + objectError.getDefaultMessage();
        }
        return defaultMessage;
    }
}
