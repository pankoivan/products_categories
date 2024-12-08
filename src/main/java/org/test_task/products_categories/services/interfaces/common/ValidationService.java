package org.test_task.products_categories.services.interfaces.common;

import org.springframework.validation.BindingResult;
import org.test_task.products_categories.exceptions.InputValidationException;
import org.test_task.products_categories.exceptions.UrlValidationException;

public interface ValidationService {

    default void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new InputValidationException(bindingResult.getFieldErrors());
        }
    }

    default Integer validateAndParsePathId(String pathId) {
        int id;
        try {
            id = Integer.parseInt(pathId);
        } catch (NumberFormatException e) {
            throw new UrlValidationException("Некорректный id: \"%s\"".formatted(pathId));
        }
        if (id <= 0) {
            throw new UrlValidationException("Некорректный id: \"%s\"".formatted(pathId));
        }
        return id;
    }

}
