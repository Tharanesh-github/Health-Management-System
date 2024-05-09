/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cw.exception;

/**
 *
 * @author Tharanesh
 */

//This code includes an exception mapper provider for handling exceptions in JAX-RS web services
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundExceptionMapper implements
        ExceptionMapper<ResourceNotFoundException> {
    
    //This line initializes a logger instance named "LOG" for the class "ResourceNotFoundExceptionMapper" using SLF4J
    private static final Logger LOG = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);
    
    //This code catches ResourceNotFoundException and logs it, returning a NOT_FOUND response with the exception message
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        LOG.error("ResourceNotFoundExceptionMapper Has Been Initialized: {}",
                exception.getMessage(), exception);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
    
}
