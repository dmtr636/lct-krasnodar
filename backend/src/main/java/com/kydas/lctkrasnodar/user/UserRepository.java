package com.kydas.lctkrasnodar.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndIdIsNot(String email, Long id);
    List<User> findAllByEmailIsNot(String email);
}
