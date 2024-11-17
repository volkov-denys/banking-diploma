package com.diploma.banking.repository;

import com.diploma.banking.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJpaDao implements UserDao {

    private final UserRepo userRepo;

    public UserJpaDao(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(Long.valueOf(id));
    }
}
