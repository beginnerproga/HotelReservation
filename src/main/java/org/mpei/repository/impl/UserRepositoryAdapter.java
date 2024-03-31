package org.mpei.repository.impl;

import lombok.RequiredArgsConstructor;
import org.mpei.exception.SameEmailException;
import org.mpei.model.User;
import org.mpei.repository.UserRepository;
import org.mpei.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        try {
            return userJpaRepository.save(user);
        } catch (Exception e) {
            throw new SameEmailException("User with this email already created");
        }
    }
}
