package br.com.resultatec.sagflix.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.resultatec.sagflix.api.v1.assembler.ComentarioVideoAssembler;
import br.com.resultatec.sagflix.api.v1.model.input.ComentarioVideoInput;
import br.com.resultatec.sagflix.api.v1.model.representation.ComentarioVideoModel;
import br.com.resultatec.sagflix.domain.model.ComentarioVideo;
import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.service.BuscarVideoService;
import br.com.resultatec.sagflix.domain.service.RegistrarComentarioVideoService;

@RestController
@RequestMapping("/videos/{videoId}/comentarios")
public class ComentarioVideoController {
    
    @Autowired private RegistrarComentarioVideoService comentarioVideoService;
    @Autowired private ComentarioVideoAssembler comentarioVideoAssembler;
    @Autowired private BuscarVideoService buscarVideoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioVideoModel comentar(@PathVariable("videoId") Long videoId, 
        @Valid @RequestBody ComentarioVideoInput comentarioVideoInput) {

        ComentarioVideo comentarioVideoRegistrado = comentarioVideoService.registrar(videoId, comentarioVideoInput.getComentario());

        return comentarioVideoAssembler.toModel(comentarioVideoRegistrado);
    }

    @GetMapping
    public List<ComentarioVideoModel> findAll(@PathVariable("videoId") Long videoId) {
        Video videoEncontrado = buscarVideoService.buscar(videoId);

        List<ComentarioVideo> comentarios = videoEncontrado.getComentarios();

        return comentarioVideoAssembler.toCollectionModel(comentarios);
    }

}
