package com.onlineshop.onlineshop.exceptions;

public class UnAuthorizedRequestException extends RuntimeException {

    private String message;
    public UnAuthorizedRequestException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
