package com.kydas.lctkrasnodar.account;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.InvalidPasswordException;
import com.kydas.lctkrasnodar.core.security.SecurityContext;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final SecurityContext securityContext;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountService(SecurityContext securityContext, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.securityContext = securityContext;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getCurrentUser() throws ApiException {
        return securityContext.getCurrentUser();
    }

    public User updateUserPassword(String currentPassword, String newPassword) throws ApiException {
        User user = securityContext.getCurrentUser();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidPasswordException()
                    .setMessage("Current password is incorrect.");
        }

        if (!isPasswordValid(newPassword)) {
            throw new InvalidPasswordException()
                    .setMessage("Invalid password format. Password must be between 8 and 50 characters long, " +
                            "contain at least one digit, and one uppercase letter.");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    private boolean isPasswordValid(String password) {
        return password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isUpperCase);
    }
}
