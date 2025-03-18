package com.youcode.nowastebackend.common.security.dto.password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(
        @NotBlank @Email String email,
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
