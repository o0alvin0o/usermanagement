package com.myth.dto;

import com.myth.enums.UserStatus;

import java.time.LocalDateTime;

public record UserDTO(
        String username,
        String email,
        UserStatus status,
        LocalDateTime creationDate,
        LocalDateTime lastLogin
) {
}
