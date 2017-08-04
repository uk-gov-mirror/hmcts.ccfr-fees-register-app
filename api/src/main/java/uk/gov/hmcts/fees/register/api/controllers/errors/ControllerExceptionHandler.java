package uk.gov.hmcts.fees.register.api.controllers.errors;


import com.google.common.collect.Iterators;
import java.util.Locale;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.gov.hmcts.fees.register.api.contract.ErrorDto;
import uk.gov.hmcts.fees.register.api.model.exceptions.ResourceNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity entityNotFoundException(ResourceNotFoundException e) {
        String message = String.format("%s for %s=%s was not found", e.getResourceName(), e.getIdName(), e.getIdValue());
        return new ResponseEntity<>(new ErrorDto(message), NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError firstFieldError = e.getBindingResult().getFieldErrors().get(0);
        String message = firstFieldError.getField() + ": " + messageSource.getMessage(firstFieldError, Locale.getDefault());
        return new ResponseEntity<>(new ErrorDto(message), BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ConstraintViolationException e) {
        ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
        String parameterName = Iterators.getLast(violation.getPropertyPath().iterator()).getName();
        return new ResponseEntity<>(new ErrorDto(parameterName + ": " + violation.getMessage()), BAD_REQUEST);
    }
}
