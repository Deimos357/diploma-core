package ua.nure.tanasiuk.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errorMessages = getMessages(ex);
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler(ApiValidationException.class)
    public ResponseEntity handleApiViolation(ApiValidationException ex) {
        return ResponseEntity.badRequest().body(singletonList(ex.getMessage()));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ResponseEntity handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return ResponseEntity.badRequest().body(singletonList(error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return ResponseEntity.badRequest().body(singletonList(error));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    private List<String> getMessages(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());
    }
}
