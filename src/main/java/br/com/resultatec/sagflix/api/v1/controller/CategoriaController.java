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

import br.com.resultatec.sagflix.api.v1.assembler.CategoriaAssembler;
import br.com.resultatec.sagflix.api.v1.model.input.CategoriaInput;
import br.com.resultatec.sagflix.api.v1.model.representation.CategoriaModel;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.repository.CategoriaRepository;
import br.com.resultatec.sagflix.domain.service.AtivarCategoriaService;
import br.com.resultatec.sagflix.domain.service.CatalogoCategoriaService;
import br.com.resultatec.sagflix.domain.service.InativarCategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private CatalogoCategoriaService catalogoCategoriaService;
    @Autowired private CategoriaAssembler categoriaAssembler;
    @Autowired private InativarCategoriaService inativarCategoriaService;
    @Autowired private AtivarCategoriaService ativarCategoriaService;

    @GetMapping
    public List<CategoriaModel> findAll() {
        return categoriaAssembler.toCollectionModel(categoriaRepository.findAll());
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaModel> findAllById(@PathVariable("categoriaId") Long categoriaId) {

        return categoriaRepository.findById(categoriaId)
        .map(categoria -> ResponseEntity.ok(categoriaAssembler.toModel(categoria)))
        .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaModel save(@Valid @RequestBody CategoriaInput categoriaInput) {
        Categoria novaCategoria = categoriaAssembler.toEntity(categoriaInput);
        Categoria categoriaSalva = catalogoCategoriaService.save(novaCategoria);

        return categoriaAssembler.toModel(categoriaSalva);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaModel> update(@PathVariable("categoriaId") Long categoriaId, @Valid @RequestBody CategoriaInput categoriaInput) {

        if (!categoriaRepository.existsById(categoriaId)) return ResponseEntity.notFound().build();
    
        Categoria novaCategoria = categoriaAssembler.toEntity(categoriaInput);

        novaCategoria.setId(categoriaId);
        Categoria categoriaSalva = catalogoCategoriaService.save(novaCategoria);
        
        return ResponseEntity.ok(categoriaAssembler.toModel(categoriaSalva));
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> delete(@PathVariable("categoriaId") Long categoriaId) {

        catalogoCategoriaService.deleteById(categoriaId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{categoriaId}/inativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable("categoriaId") Long categoriaId) {
        inativarCategoriaService.inativar(categoriaId);
    }

    @PutMapping("/{categoriaId}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarinativar(@PathVariable("categoriaId") Long categoriaId) {
        ativarCategoriaService.ativar(categoriaId);
    }
        
}
