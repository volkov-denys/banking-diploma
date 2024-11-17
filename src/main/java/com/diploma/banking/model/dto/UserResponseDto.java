package com.diploma.banking.model.dto;

import com.diploma.banking.model.User;

import java.time.LocalDate;

public record UserResponseDto(
        String login,
        String name,
        String surname,
        String documentNumber,
        LocalDate birthDate,
        LocalDate createdAt
) {

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getLogin(),
                user.getName(),
                user.getSurname(),
                user.getDocumentNumber(),
                user.getBirthDate(),
                user.getCreatedAt()
        );
    }
}
