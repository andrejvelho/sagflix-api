package br.com.resultatec.sagflix.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(name = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 10)
    private String cor;
    
    @Size(max = 255)
    private String link;

    @Embedded
    private LinkExtra linkExtra;

    @Enumerated(EnumType.STRING)
    private StatusCategoria statusCategoria;

    private LocalDateTime dataCadastro;

    public void publicar() {

        if (this.getId() == null) {
            this.setDataCadastro(LocalDateTime.now());
            this.setStatusCategoria(StatusCategoria.ATIVO);
        }
        
    }
}
