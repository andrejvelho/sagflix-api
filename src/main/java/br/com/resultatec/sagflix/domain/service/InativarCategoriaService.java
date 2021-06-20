package br.com.resultatec.sagflix.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;

@Service
public class InativarCategoriaService {
    

    @Autowired private BuscarCategoriaService buscarCategoriaService;
    @Autowired private CategoriaRepository categoriaRepository;

    @Transactional
    public void inativar(Long categoriaId) {
        Categoria categoria = buscarCategoriaService.buscar(categoriaId);

        categoria.inativar();

        categoriaRepository.save(categoria);
        
    }
}
