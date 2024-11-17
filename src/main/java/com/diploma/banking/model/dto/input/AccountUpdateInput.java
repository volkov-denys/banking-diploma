package com.diploma.banking.model.dto.input;

public record AccountUpdateInput(
        Double amountToChange, // if negative - withdrawal, if positive - deposit
        Double apyToChange
) {
}
