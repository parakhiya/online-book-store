package com.example.bookstore.exceptions;

import com.example.bookstore.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

import static com.example.bookstore.exceptions.ErrorCodes.INVALID_PAYLOAD_FORMAT;
import static com.example.bookstore.exceptions.ErrorCodes.INVALID_PAYLOAD_FORMAT_FOR_NOT_READABLE_MESSAGES;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponse> handleCustomExceptions(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage(), e.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleInvalidUrlParamExceptions(MethodArgumentTypeMismatchException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage(), e.getClass().getSimpleName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidArgumentsExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse(INVALID_PAYLOAD_FORMAT, StringUtils.join(errors), "MethodArgumentNotValidException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleInvalidFormatExceptions(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = new ErrorResponse(INVALID_PAYLOAD_FORMAT_FOR_NOT_READABLE_MESSAGES, e.getMessage(), "HttpMessageNotReadableException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
