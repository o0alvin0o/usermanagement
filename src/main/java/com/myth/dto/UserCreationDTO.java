package com.myth.dto;

import com.myth.constant.App;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserCreationDTO(
        @Pattern(regexp = App.USERNAME_REGEX, message = "Username does not meet requirement(s)")
        String username,
        @Pattern(regexp = App.PASSWORD_REGEX, message = "Password does not meet requirement(s)")
        String password,
        @Email(message = "Email does not valid")
        String email
) {

}
