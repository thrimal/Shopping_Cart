package com.wixis360.spring.advisor;

import com.wixis360.spring.util.StandraResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(RuntimeException ex){
        StandraResponse response = new StandraResponse(500, "Error", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
