package org.test_task.products_categories.controllers.errors.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class FieldsValidationExceptionResponseEntity extends ExceptionResponseEntity {

    private List<FieldErrorPair> fieldErrorPairs;

    public FieldsValidationExceptionResponseEntity(List<FieldError> fieldErrors) {
        super(HttpStatus.BAD_REQUEST.value(), true);
        this.fieldErrorPairs = fieldErrors.stream().map(FieldErrorPair::new).toList();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class FieldErrorPair {

        private String fieldName;

        private String fieldErrorMessage;

        public FieldErrorPair(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }

}
