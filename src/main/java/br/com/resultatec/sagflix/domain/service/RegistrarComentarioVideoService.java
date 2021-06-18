package br.com.resultatec.sagflix.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.model.ComentarioVideo;
import br.com.resultatec.sagflix.domain.model.Video;

@Service
public class RegistrarComentarioVideoService {
    

    @Autowired private BuscarVideoService buscarVideoService;

    @Transactional
    public ComentarioVideo registrar(Long videoId, String comentario) {

        Video video = buscarVideoService.buscar(videoId);

        return video.adicionarNovoComentario(comentario);

    }
}
