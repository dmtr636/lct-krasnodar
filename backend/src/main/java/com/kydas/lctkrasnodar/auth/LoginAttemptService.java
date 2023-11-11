package com.kydas.lctkrasnodar.auth;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.LoginFailedException;
import com.kydas.lctkrasnodar.core.exceptions.ThrottledException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.kydas.lctkrasnodar.core.request.RequestUtils.getIpAddress;

@Service
public class LoginAttemptService {

    private final LoginAttemptRepository loginAttemptRepository;

    private LoginAttempt loginAttempt;
    public final static int ATTEMPTS = 6;
    public final static int BAN_DURATION_SECONDS = 300;

    @Autowired
    public LoginAttemptService(LoginAttemptRepository loginAttemptRepository) {
        this.loginAttemptRepository = loginAttemptRepository;
    }

    public void reset() {
        loginAttempt.setAttempts(0);
        loginAttempt.setBanExpirationTime(null);
        loginAttemptRepository.save(loginAttempt);
    }

    public void getOrCreateLoginAttempt(HttpServletRequest request) {
        String ipAddress = getIpAddress(request);
        this.loginAttempt = loginAttemptRepository.findByIp(ipAddress).orElseGet(() ->
            new LoginAttempt(ipAddress)
        );
        checkLastLogin();
        loginAttempt.setLastLoginAttempt(LocalDateTime.now());
        loginAttemptRepository.save(loginAttempt);
    }

    public void handleSuccessfulAttempt() throws ServletException {
        loginAttemptRepository.delete(loginAttempt);
    }

    public void handleFailedAttempt() throws ApiException {
        incrementAttempts();
        int remainingAttempts = getRemainingAttempts();

        if (remainingAttempts == 0) {
            setBan();
            throw new ThrottledException(getBanDurationSeconds());
        }

        throw new LoginFailedException(remainingAttempts);
    }

    public void assertAttemptAvailable() throws ApiException {
        if (isBanned()) {
            throw new ThrottledException(getBanDurationSeconds());
        }
    }

    private void checkLastLogin() {
        LocalDateTime now = LocalDateTime.now();
        long secondsSinceLastLogin = ChronoUnit.SECONDS.between(loginAttempt.getLastLoginAttempt(), now);

        if (secondsSinceLastLogin >= BAN_DURATION_SECONDS) {
            loginAttempt.setAttempts(0);
        }
    }

    private boolean isBanned() {
        LocalDateTime banExpirationTime = loginAttempt.getBanExpirationTime();
        return banExpirationTime != null && banExpirationTime.isAfter(LocalDateTime.now());
    }

    private void incrementAttempts() {
        int attempts = loginAttempt.getAttempts();
        loginAttempt.setAttempts(++attempts);
        loginAttemptRepository.save(loginAttempt);
    }

    private void setBan() {
        loginAttempt.setBanExpirationTime(LocalDateTime.now().plusSeconds(BAN_DURATION_SECONDS));
        loginAttemptRepository.save(loginAttempt);
    }

    private int getRemainingAttempts() {
        int attempts = loginAttempt.getAttempts();
        if (attempts > 0 && attempts % ATTEMPTS == 0) {
            return 0;
        }
        return ATTEMPTS - (attempts % ATTEMPTS);
    }

    private int getBanDurationSeconds() {
        LocalDateTime banExpirationTime = loginAttempt.getBanExpirationTime();
        if (banExpirationTime != null) {
            double remainingSeconds = (ChronoUnit.MILLIS.between(LocalDateTime.now(), banExpirationTime) / 1000.0);
            return (int) Math.max(1, Math.round(remainingSeconds));
        }
        return 0;
    }
}