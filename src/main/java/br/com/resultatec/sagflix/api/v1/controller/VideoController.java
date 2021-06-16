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

import br.com.resultatec.sagflix.api.v1.assembler.VideoAssembler;
import br.com.resultatec.sagflix.api.v1.model.input.VideoInput;
import br.com.resultatec.sagflix.api.v1.model.representation.VideoModel;
import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.repository.VideoRepository;
import br.com.resultatec.sagflix.domain.service.CatalogoVideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired 
    private VideoRepository videoRepository;
    
    @Autowired 
    private CatalogoVideoService catalogoVideoService;
    
    @Autowired
    private VideoAssembler videoAssembler;

    @GetMapping
    public List<VideoModel> findAll() {
        return videoAssembler.toCollectionModel(videoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VideoModel> insert(@Valid @RequestBody VideoInput videoInput) {

        Video novoVideo = videoAssembler.toEntity(videoInput);
        Video videoSalvo = catalogoVideoService.save(novoVideo);

        return ResponseEntity.ok(videoAssembler.toModel(videoSalvo));
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoModel> findAllById(@PathVariable("videoId") Long videoId) {

        return videoRepository.findById(videoId)
        .map(video -> ResponseEntity.ok(videoAssembler.toModel(video)))
        .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<VideoModel> update(@PathVariable("videoId") Long videoId, @Valid @RequestBody VideoInput videoInput) {

        if (!videoRepository.existsById(videoId)) return ResponseEntity.notFound().build();
    
    
        Video novoVideo = videoAssembler.toEntity(videoInput);

        novoVideo.setId(videoId);
        Video videoSalvo = catalogoVideoService.save(novoVideo);
        
        return ResponseEntity.ok(videoAssembler.toModel(videoSalvo));
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> delete(@PathVariable("videoId") Long videoId) {

        videoRepository.deleteById(videoId);

        return ResponseEntity.noContent().build();
    }
    
}
