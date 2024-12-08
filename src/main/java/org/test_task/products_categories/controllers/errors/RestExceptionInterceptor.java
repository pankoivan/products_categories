package org.test_task.products_categories.controllers.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.test_task.products_categories.controllers.errors.entities.FieldsValidationExceptionResponseEntity;
import org.test_task.products_categories.controllers.errors.entities.NoFieldsValidationExceptionResponseEntity;
import org.test_task.products_categories.exceptions.FileUploadingException;
import org.test_task.products_categories.exceptions.InputValidationException;

@ControllerAdvice
@Order(1000)
@PreAuthorize("permitAll()")
@Slf4j
public class RestExceptionInterceptor {

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> badRequest(InputValidationException e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<FieldsValidationExceptionResponseEntity> badRequest(InputValidationException e) {
        FieldsValidationExceptionResponseEntity json = new FieldsValidationExceptionResponseEntity(e.getFieldErrors());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileUploadingException.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> internalServer(FileUploadingException e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
