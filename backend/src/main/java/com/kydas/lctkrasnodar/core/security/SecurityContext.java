package com.kydas.lctkrasnodar.core.security;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.AuthenticationException;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContext {
    private final UserRepository userRepository;

    public User getCurrentUser() throws ApiException {
        UserDetailsImpl userDetails = (UserDetailsImpl) getAuthentication().getPrincipal();
        return userRepository
            .findById(userDetails.getUser().getId())
            .orElseThrow(AuthenticationException::new);
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
