package ua.nure.tanasiuk.common;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ApiValidationException extends RuntimeException {

    public ApiValidationException(String msg) {
        super(msg);
    }

    public ApiValidationException(List<ObjectError> allErrors) {
    }
}
