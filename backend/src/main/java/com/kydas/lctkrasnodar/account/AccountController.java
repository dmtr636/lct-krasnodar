package com.kydas.lctkrasnodar.account;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.user.UserDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/me")
    public UserDTO currentUser() throws ApiException {
        return new UserDTO(accountService.getCurrentUser());
    }

    @PutMapping("/updatePassword")
    public UserDTO updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO) throws ApiException {
        return new UserDTO(accountService
            .updateUserPassword(passwordUpdateDTO.getCurrentPassword(), passwordUpdateDTO.getNewPassword()));
    }
}
