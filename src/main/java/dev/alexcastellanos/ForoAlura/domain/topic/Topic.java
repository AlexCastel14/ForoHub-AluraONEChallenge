package dev.alexcastellanos.ForoAlura.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private String mensaje;
    private String nombreCurso;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private Boolean active;

    public Topic(RegisterTopicData registerTopicData) {
        this.idUsuario = registerTopicData.idUsuario();
        this.mensaje = registerTopicData.mensaje();
        this.nombreCurso = registerTopicData.nombreCurso();
        this.titulo = registerTopicData.titulo();
        this.fechaCreacion = LocalDateTime.now();
        this.active = Boolean.TRUE;
    }

    public void updateTopic(UpdateTopicData updateTopicData){
        if (updateTopicData.idUsuario() != null){
            this.idUsuario = updateTopicData.idUsuario();
        }
        if (updateTopicData.mensaje() != null){
            this.mensaje = updateTopicData.mensaje();
        }
        if (updateTopicData.nombreCurso() != null){
            this.nombreCurso = updateTopicData.nombreCurso();
        }
        if (updateTopicData.titulo() != null){
            this.titulo = updateTopicData.titulo();
        }
    }

    public void deleteTopic(){
        this.active = Boolean.FALSE;
    }
}
