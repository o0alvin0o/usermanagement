package com.myth.dto;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        @Email(message = "Email does not valid")
        String email
) {
}
