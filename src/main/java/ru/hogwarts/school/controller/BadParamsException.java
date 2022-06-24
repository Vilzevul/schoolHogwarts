package ru.hogwarts.school.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
//@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadParamsException extends RuntimeException{

    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleException(NotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}