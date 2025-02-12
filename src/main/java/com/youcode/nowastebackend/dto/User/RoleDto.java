package com.youcode.nowastebackend.dto.User;

import jakarta.validation.constraints.NotBlank;

public record RoleDto(

      @NotBlank String name
) {
}
