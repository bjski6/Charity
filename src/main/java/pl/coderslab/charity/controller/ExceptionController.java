package pl.coderslab.charity.controller;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public String notFoundException (){
        return "/exception";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String userExistException (){
        return "/userException";
    }
}
