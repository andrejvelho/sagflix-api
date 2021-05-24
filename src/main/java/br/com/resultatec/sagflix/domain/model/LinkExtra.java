package br.com.resultatec.sagflix.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class LinkExtra {

    @Size(max = 255)
    private String text;

    @Size(max = 255)
    private String url;
}