package com.kydas.lctkrasnodar.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDTO {

    @NotNull
    private String currentPassword;

    @NotNull
    @Size(min = 8, max = 50)
    private String newPassword;
}
