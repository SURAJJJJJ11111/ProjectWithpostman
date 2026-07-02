package com.example.demo.exception;

public class InvalidDesignationException extends RuntimeException {

    public InvalidDesignationException(String message) {
        super(message);
    }
}