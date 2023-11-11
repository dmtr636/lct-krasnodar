package com.kydas.lctkrasnodar.auth;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserDTO login(
        @Valid @RequestBody LoginRequest data, HttpServletRequest request, HttpServletResponse response
    ) throws ApiException {
        User user = authService.login(data, request, response);
        return new UserDTO(user);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException, ApiException {
        authService.logout(request);
    }

    @PostMapping("/reset")
    public void reset(HttpServletRequest request) throws ServletException {
        authService.reset(request);
    }
}
