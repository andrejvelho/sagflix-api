package br.com.resultatec.sagflix.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.repository.VideoRepository;
import br.com.resultatec.sagflix.domain.service.CatalogoVideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired VideoRepository videoRepository;
    
    @Autowired CatalogoVideoService catalogoVideoService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Video insert(@Valid @RequestBody Video video) {
        return catalogoVideoService.save(video);
    }

    @GetMapping
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

}
