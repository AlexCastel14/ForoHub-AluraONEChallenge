package dev.alexcastellanos.ForoAlura.domain.topic;

public record ListTopicData(Long id, String titulo, String mensaje) {
    public ListTopicData(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getMensaje());
    }
}
