package org.test_task.products_categories.services.common;

import org.springframework.validation.BindingResult;
import org.test_task.products_categories.exceptions.InputValidationException;

public interface ValidationService {

    default void bindingResultValidation(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new InputValidationException(bindingResult.getFieldErrors());
        }
    }

}
