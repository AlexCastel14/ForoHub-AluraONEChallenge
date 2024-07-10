package dev.alexcastellanos.ForoAlura.domain.topic;

import java.time.LocalDateTime;

public record ResponseTopicData(Long id, String mensaje, String titulo, LocalDateTime fechaCreacion, String nombreCurso) {
    public ResponseTopicData(Topic topic){
        this(topic.getId(), topic.getMensaje(), topic.getTitulo(), topic.getFechaCreacion(), topic.getNombreCurso());
    }
}
