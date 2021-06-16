package br.com.resultatec.sagflix.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.resultatec.sagflix.api.v1.model.input.CategoriaInput;
import br.com.resultatec.sagflix.api.v1.model.representation.CategoriaModel;
import br.com.resultatec.sagflix.domain.model.Categoria;

@Component
public class CategoriaAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public CategoriaModel toModel(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaModel.class);
    }

    public List<CategoriaModel> toCollectionModel(List<Categoria> categorias) {
        return categorias
        .stream()
        .map(this::toModel)
        .collect(Collectors.toList());
    }

    public Categoria toEntity(CategoriaInput categoriaInput) {
        return modelMapper.map(categoriaInput, Categoria.class);
    }

}
