package com.example.student_management.exceptions;

import com.example.student_management.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalStudentExceptionHandler {

    /****** Handle Global Exception *********/
    @ExceptionHandler
    private ResponseEntity<CommonResponse<StudentErrorResponse>> globalExceptionHandler(Exception exception) {
        CommonResponse<StudentErrorResponse> globalExceptionHandlerCommonResponse = new CommonResponse<>();
        globalExceptionHandlerCommonResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        globalExceptionHandlerCommonResponse.setResponseMessage(exception.getMessage());
        globalExceptionHandlerCommonResponse.setResponseObject(null);

        return new ResponseEntity<>(globalExceptionHandlerCommonResponse,HttpStatus.BAD_REQUEST);
    }
}
