package com.youcode.youquiz.exceptions;

public class MaxAttemptsReachedException extends RuntimeException {
    public MaxAttemptsReachedException(String message) {
        super(message);
    }
}
