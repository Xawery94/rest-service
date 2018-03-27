package com.company.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Course for this student doesn't exist.")
public class StudentCourseFoundException extends RuntimeException {
}
