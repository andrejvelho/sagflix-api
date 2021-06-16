package br.com.resultatec.sagflix.api.v1.model.representation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VideoModel {
    
    private Long id;
    private String titulo;
    private String url;
    private CategoriaModel categoria;

}
