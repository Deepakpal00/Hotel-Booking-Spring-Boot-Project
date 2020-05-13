package com.alore.growth.io.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ApiError {

    private int code;
    private String message;
    private Instant timestamp;

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now();
    }
}
