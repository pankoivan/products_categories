package org.test_task.products_categories.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class InputValidationException extends RuntimeException {

    private final List<FieldError> fieldErrors;

    public InputValidationException(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public InputValidationException(List<FieldError> fieldErrors, String msg) {
        super(msg);
        this.fieldErrors = fieldErrors;
    }

    public InputValidationException(List<FieldError> fieldErrors, String msg, Throwable cause) {
        super(msg, cause);
        this.fieldErrors = fieldErrors;
    }

}
