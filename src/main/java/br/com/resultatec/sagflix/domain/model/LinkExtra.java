package br.com.resultatec.sagflix.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class LinkExtra {

    @NotBlank
    @Size(max = 255)
    private String text;

    @NotBlank
    @Size(max = 255)
    private String url;
}