package com.youcode.nowastebackend.common.exception;

class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
