package com.diploma.banking.model.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UserInput(
        @NotNull
        @NotEmpty
        @Length(min = 3, max = 20)
        String login,
        @NotNull
        @NotEmpty
        @Length(min = 3, max = 20)
        String password,
        @NotNull
        @NotEmpty
        String name,
        @NotNull
        @NotEmpty
        String surname,
        @NotNull
        @NotEmpty
        String documentNumber,
        @NotNull
        LocalDate birthDate
) {
}
