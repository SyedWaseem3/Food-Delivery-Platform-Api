package com.geekster.FooDeliveryPlatformApi.model.customExceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}

