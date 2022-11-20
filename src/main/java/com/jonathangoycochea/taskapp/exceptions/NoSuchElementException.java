package com.jonathangoycochea.taskapp.exceptions;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() {
        super("The requested value is not present");
    }
}
