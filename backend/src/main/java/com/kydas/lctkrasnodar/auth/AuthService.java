package com.kydas.lctkrasnodar.auth;

import com.kydas.lctkrasnodar.audit.AuditEvent;
import com.kydas.lctkrasnodar.audit.AuditService;
import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.security.SecurityContext;
import com.kydas.lctkrasnodar.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RememberMeServices rememberMeServices;
    private final SecurityContext securityContext;
    private final LoginAttemptService loginAttemptService;
    private final AuditService auditService;

    public User login(LoginRequest data, HttpServletRequest request, HttpServletResponse response) throws ApiException {
        authenticateUser(data.getEmail(), data.getPassword(), request);
        if (data.getRememberMe()) {
            rememberMeServices.loginSuccess(request, response, securityContext.getAuthentication());
        }
        auditService.addEvent(AuditEvent.EventType.LOGIN);
        return securityContext.getCurrentUser();
    }

    public void logout(HttpServletRequest request) throws ServletException, ApiException {
        auditService.addEvent(AuditEvent.EventType.LOGOUT);
        request.logout();
    }

    public void reset(HttpServletRequest request) {
        loginAttemptService.getOrCreateLoginAttempt(request);
        loginAttemptService.reset();
    }

    private void authenticateUser(String email, String password, HttpServletRequest request) throws ApiException {
        loginAttemptService.getOrCreateLoginAttempt(request);
        loginAttemptService.assertAttemptAvailable();
        try {
            request.logout();
            request.login(email, password);
            loginAttemptService.handleSuccessfulAttempt();
        } catch (ServletException e) {
            loginAttemptService.handleFailedAttempt();
        }
    }
}
