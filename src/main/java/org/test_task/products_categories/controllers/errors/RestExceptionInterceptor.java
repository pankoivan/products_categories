package org.test_task.products_categories.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.test_task.products_categories.controllers.errors.entities.FieldsValidationExceptionResponseEntity;
import org.test_task.products_categories.controllers.errors.entities.NotFieldsValidationExceptionResponseEntity;
import org.test_task.products_categories.exceptions.EntityNotFoundException;
import org.test_task.products_categories.exceptions.FileOperationsException;
import org.test_task.products_categories.exceptions.InputFieldsValidationException;
import org.test_task.products_categories.exceptions.UrlValidationException;

@ControllerAdvice
@PreAuthorize("permitAll()")
public class RestExceptionInterceptor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputFieldsValidationException.class)
    public ResponseEntity<FieldsValidationExceptionResponseEntity> badRequest(InputFieldsValidationException e) {
        FieldsValidationExceptionResponseEntity json = new FieldsValidationExceptionResponseEntity(e.getFieldErrors());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UrlValidationException.class)
    public ResponseEntity<NotFieldsValidationExceptionResponseEntity> badRequest(UrlValidationException e) {
        NotFieldsValidationExceptionResponseEntity json = new NotFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NotFieldsValidationExceptionResponseEntity> notFound(EntityNotFoundException e) {
        NotFieldsValidationExceptionResponseEntity json = new NotFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileOperationsException.class)
    public ResponseEntity<NotFieldsValidationExceptionResponseEntity> internalServer(FileOperationsException e) {
        NotFieldsValidationExceptionResponseEntity json = new NotFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
