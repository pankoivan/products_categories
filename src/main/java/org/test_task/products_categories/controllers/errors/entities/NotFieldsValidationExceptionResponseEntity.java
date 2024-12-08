package org.test_task.products_categories.controllers.errors.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class NotFieldsValidationExceptionResponseEntity extends ExceptionResponseEntity {

    private String errorDescription;

    public NotFieldsValidationExceptionResponseEntity(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value());
        this.errorDescription = errorDescription;
    }

}
