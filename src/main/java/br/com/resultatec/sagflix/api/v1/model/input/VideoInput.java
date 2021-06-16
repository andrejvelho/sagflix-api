package br.com.resultatec.sagflix.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoInput {
    
    @Size(max = 255)
    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 255)
    private String url;

    @Valid
    @NotNull
    CategoriaIdInput categoria;
}
