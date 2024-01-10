package com.microservice.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    ResourceNotFoundException()
    {
        super("Resource not Found in the DB");
    }
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
