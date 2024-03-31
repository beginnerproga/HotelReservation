package org.mpei.exception;

public class HotelAlreadyBookedException extends RuntimeException {
    public HotelAlreadyBookedException(String message) {
        super(message);
    }
}
