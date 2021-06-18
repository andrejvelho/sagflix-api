package br.com.resultatec.sagflix.api.v1.model.representation;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioVideoModel {
    
    private Long id;
    private String comentario;
    private OffsetDateTime dataRegistro;

}
