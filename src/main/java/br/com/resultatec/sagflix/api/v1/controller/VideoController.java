package br.com.resultatec.sagflix.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.service.CatalogoVideoService;


@RestController
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired CatalogoVideoService manterVideoService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Video insert(@Valid @RequestBody Video video) {
        return manterVideoService.save(video);
    }
}
