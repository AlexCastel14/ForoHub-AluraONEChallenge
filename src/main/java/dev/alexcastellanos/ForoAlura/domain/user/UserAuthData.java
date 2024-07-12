package dev.alexcastellanos.ForoAlura.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
