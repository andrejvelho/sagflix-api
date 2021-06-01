package br.com.resultatec.sagflix.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.resultatec.sagflix.domain.validation.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Video {
 
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    private String url;

    @NotNull
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @Valid
    @ManyToOne
    private Categoria categoria;

}
