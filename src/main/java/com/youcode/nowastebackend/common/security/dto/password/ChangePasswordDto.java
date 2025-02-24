package com.youcode.nowastebackend.common.security.dto.password;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
