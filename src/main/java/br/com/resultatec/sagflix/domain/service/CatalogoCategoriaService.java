package br.com.resultatec.sagflix.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.resultatec.sagflix.domain.exception.NegocioExeption;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;

@Service
public class CatalogoCategoriaService {
    
    @Autowired 
    CategoriaRepository categoriaRepository;

    @Autowired private BuscarCategoriaService buscarCategoriaService;

    @Transactional
    public Categoria save(Categoria categoria) {

        boolean tituloCategoriaEmUso = categoriaRepository
        .findByTitulo(categoria.getTitulo())
        .stream()
        .anyMatch(c -> !c.equals(categoria));

        if (tituloCategoriaEmUso) throw new NegocioExeption("categoria.titulo.em.uso");

        categoria.publicar();

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteById(Long categoriaId) {

        Categoria categoriaIsExist = buscarCategoriaService.buscar(categoriaId);

        categoriaRepository.delete(categoriaIsExist);
    }

  
}
