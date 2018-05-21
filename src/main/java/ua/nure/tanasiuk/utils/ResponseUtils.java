package ua.nure.tanasiuk.utils;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public final class ResponseUtils {
    private ResponseUtils() {
    }

    public static List<String> failureFromObjectErrors(List<ObjectError> errors) {
        return errors.stream().map(ObjectError::getCode).collect(Collectors.toList());
    }
}
