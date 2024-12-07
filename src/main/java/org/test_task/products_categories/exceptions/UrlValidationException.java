package org.test_task.products_categories.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlValidationException extends RuntimeException {

    public UrlValidationException() {

    }

    public UrlValidationException(String msg) {
        super(msg);
    }

    public UrlValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
