package br.com.resultatec.sagflix.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;
import br.com.resultatec.sagflix.domain.service.CatalogoCategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired 
    CategoriaRepository categoriaRepository;
    
    @Autowired
    CatalogoCategoriaService catalogoCategoriaService;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> findAllById(@PathVariable("categoriaId") Long categoriaId) {

        return categoriaRepository.findById(categoriaId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria insert(@Valid @RequestBody Categoria categoria) {
        return catalogoCategoriaService.save(categoria);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> update(@PathVariable("categoriaId") Long categoriaId, @Valid @RequestBody Categoria categoria) {

        if (!categoriaRepository.existsById(categoriaId)) return ResponseEntity.notFound().build();
    
        categoria.setId(categoriaId);
        categoria = catalogoCategoriaService.save(categoria);
        
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> delete(@PathVariable("categoriaId") Long categoriaId) {


        catalogoCategoriaService.deleteById(categoriaId);

        return ResponseEntity.noContent().build();
    }

    
}
