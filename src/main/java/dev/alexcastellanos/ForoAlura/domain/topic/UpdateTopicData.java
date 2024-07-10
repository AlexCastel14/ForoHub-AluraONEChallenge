package dev.alexcastellanos.ForoAlura.domain.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(
        @NotNull
        Long id,
        Long idUsuario,
        String mensaje,
        String nombreCurso,
        String titulo
) {
}
