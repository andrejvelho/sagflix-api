package br.com.resultatec.sagflix.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.exception.EntidadeNaoEncontraException;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;

@Service
public class BuscarCategoriaService {
 
    @Autowired private CategoriaRepository categoriaRepository;
    
    public Categoria buscar(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntidadeNaoEncontraException("categoria.nao.encontrada"));
    }

    public boolean exists(Long categoriaId) {
        return categoriaRepository.existsById(categoriaId);
    }

    public boolean notExist(Long categoriaId) {
        return !exists(categoriaId);
    }

}
