/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.exception;

/**
 *
 * @author Tharanesh
 */

//These imports are necessary for handling exceptions and providing custom responses in a RESTful web service.
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    
    //This code initializes a logger instance for logging exceptions related to user not found scenarios
    private static final Logger log = LoggerFactory.getLogger(UserNotFoundExceptionMapper.class);
    
    //This method defines how to handle instances of UserNotFoundException when encountered. It logs the exception and returns a response with a status of 404 (Not Found), the exception message as the response entity, and the response type as plain text
    @Override
    public Response toResponse(UserNotFoundException exception) {
        log.error("UserNotFoundException has been encountered: {}", exception.getMessage(), exception);

        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
    
    
}
