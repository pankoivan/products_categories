package org.test_task.products_categories.controllers.errors.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class NoFieldsValidationExceptionResponseEntity extends ExceptionResponseEntity {

    private String errorDescription;

    public NoFieldsValidationExceptionResponseEntity(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), false);
        this.errorDescription = errorDescription;
    }

}
