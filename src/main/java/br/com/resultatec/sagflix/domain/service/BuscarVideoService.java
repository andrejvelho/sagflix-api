package br.com.resultatec.sagflix.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.exception.EntidadeNaoEncontraException;
import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.repository.VideoRepository;

@Service
public class BuscarVideoService {
 
    @Autowired private VideoRepository videoRepository;

    public Video buscar(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new EntidadeNaoEncontraException("video.nao.encontrada"));
    }
}
