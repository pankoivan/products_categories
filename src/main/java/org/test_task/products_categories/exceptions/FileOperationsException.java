package org.test_task.products_categories.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileOperationsException extends RuntimeException {

    public FileOperationsException() {

    }

    public FileOperationsException(String msg) {
        super(msg);
    }

    public FileOperationsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
