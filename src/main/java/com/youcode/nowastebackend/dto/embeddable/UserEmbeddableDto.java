package com.youcode.nowastebackend.dto.embeddable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserEmbeddableDto(@NotNull Long id,
                                @NotBlank String name,
                                @NotBlank String email,
                                @NotBlank String password,
                                @NotBlank String phone) {
}
