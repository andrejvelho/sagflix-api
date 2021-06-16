package br.com.resultatec.sagflix.api.v1.model.representation;

import java.time.OffsetDateTime;

import br.com.resultatec.sagflix.domain.model.StatusCategoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaModel {
    
    private Long id;

    private String titulo;
    private String cor;
    private String link;
 
    private LinkExtraModel linkExtra;
    private OffsetDateTime dataCadastro;
    private StatusCategoria status;

}
