package com.kydas.lctkrasnodar.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Boolean rememberMe;
}
