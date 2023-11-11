package com.kydas.lctkrasnodar.core.exceptions;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class LoginFailedException extends ApiException {
    public LoginFailedException(Integer remainingAttempts) {
        this.setData(Map.of("remainingAttempts", remainingAttempts));
    }
}

