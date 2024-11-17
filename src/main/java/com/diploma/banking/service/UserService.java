package com.diploma.banking.service;

import com.diploma.banking.model.User;
import com.diploma.banking.model.dto.input.UserInput;
import com.diploma.banking.repository.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User fetchById(Integer id) {
        return userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User is not found by id: " + id));
    }

    public User createUser(UserInput userInput) {
        String password = passwordEncoder.encode(userInput.password());
        User user = new User(
                userInput.login(),
                password,
                userInput.name(),
                userInput.surname(),
                userInput.documentNumber(),
                userInput.birthDate(),
                LocalDate.now()
        );
        return userDao.save(user);
    }
}
