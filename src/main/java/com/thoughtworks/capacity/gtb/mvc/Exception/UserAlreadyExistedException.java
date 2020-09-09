package com.thoughtworks.capacity.gtb.mvc.Exception;

public class UserAlreadyExistedException extends Exception{
    public UserAlreadyExistedException(String message) {
        super(message);
    }
}
