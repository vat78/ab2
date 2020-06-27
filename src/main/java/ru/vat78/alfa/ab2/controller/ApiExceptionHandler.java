package ru.vat78.alfa.ab2.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vat78.alfa.ab2.exceptions.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse userNotFound(NotFoundException ex) {
        return new ErrorResponse("user not found");
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String status;
    }
}
