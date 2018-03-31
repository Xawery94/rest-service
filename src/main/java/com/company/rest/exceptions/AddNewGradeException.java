package com.company.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Grade should be between 2.0 and 5.0")
public class AddNewGradeException extends RuntimeException{
}
