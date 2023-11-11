package com.kydas.lctkrasnodar.core.security;

import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException(username)
        );
        return new UserDetailsImpl(user);
    }
}
