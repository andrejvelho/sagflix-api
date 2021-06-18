package br.com.resultatec.sagflix.domain.model;

import java.time.OffsetDateTime;

import javax.validation.Valid;

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
import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.resultatec.sagflix.domain.validation.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Categoria {

    @NotNull(groups = ValidationGroups.ClienteId.class)
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
    @NotBlank
    private String link;

    @Valid
    @NotNull
    @Embedded
    private LinkExtra linkExtra;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusCategoria statusCategoria;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;
 
    @OneToMany(mappedBy = "categoria")
    private List<Video> videos = new ArrayList<>();

    public void publicar() {

        if (this.getId() == null) {
            this.setDataCadastro(OffsetDateTime.now());
            this.setStatusCategoria(StatusCategoria.ATIVO);
        }
        
    }
}
