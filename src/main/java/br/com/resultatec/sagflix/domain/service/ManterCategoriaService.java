package br.com.resultatec.sagflix.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.resultatec.sagflix.domain.exception.EntidadeNaoEncontraException;
import br.com.resultatec.sagflix.domain.exception.NegocioExeption;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;

@Service
public class ManterCategoriaService {
    
    @Autowired 
    CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria save(Categoria categoria) {

        boolean nomeCategoriaEmUso = categoriaRepository
        .findByTitulo(categoria.getTitulo())
        .stream()
        .anyMatch(c -> !c.equals(categoria));

        if (nomeCategoriaEmUso) throw new NegocioExeption("Categoria.titulo.em.uso");

        categoria.publicar();

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteById(Long categoriaId) {

        Categoria categoriaIsExist = buscarOuFalhar(categoriaId);

        categoriaRepository.delete(categoriaIsExist);
    }

    public Categoria buscarOuFalhar(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new EntidadeNaoEncontraException("Não encontramos a pessoa com esse identificador"));
    }
}
