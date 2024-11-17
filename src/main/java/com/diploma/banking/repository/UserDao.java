package com.diploma.banking.repository;

import com.diploma.banking.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByLogin(String login);
    User save(User user);
    Optional<User> findById(Integer id);
}
