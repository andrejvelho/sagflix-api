package br.com.resultatec.sagflix.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioVideoInput {
    
    @NotBlank
    private String comentario;
}
