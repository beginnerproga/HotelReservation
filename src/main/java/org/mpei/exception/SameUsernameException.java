package org.mpei.exception;

public class SameUsernameException extends RuntimeException{
    public SameUsernameException(String message) {
        super(message);
    }
}
