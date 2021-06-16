package br.com.resultatec.sagflix.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkExtraInput {
    
    @NotBlank
    @Size(max = 255)
    private String text;

    @NotBlank
    @Size(max = 255)
    private String url;
}
