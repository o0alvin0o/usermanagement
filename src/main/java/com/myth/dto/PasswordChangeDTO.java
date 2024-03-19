package com.myth.dto;

import com.myth.constant.App;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PasswordChangeDTO(
        @NotBlank(message = "Username must be provided")
        String username,

        @Pattern(regexp = App.PASSWORD_REGEX, message = "New password does not meet requirement(s)")
        String newPassword
) {
}
