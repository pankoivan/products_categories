package org.test_task.products_categories.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileUploadingException extends RuntimeException {

    public FileUploadingException() {

    }

    public FileUploadingException(String msg) {
        super(msg);
    }

    public FileUploadingException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
