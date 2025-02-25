package com.youcode.nowastebackend.common.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppUserRequestDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String address,
        @NotBlank String phone,
        @NotNull Long role_id
) {

}
