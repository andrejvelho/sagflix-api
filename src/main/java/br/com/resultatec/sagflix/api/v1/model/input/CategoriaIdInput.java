package br.com.resultatec.sagflix.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaIdInput {
    
    @NotNull
    private Long id;
}
