package br.com.resultatec.sagflix.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.exception.NegocioExeption;
import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.repository.VideoRepository;

@Service
public class CatalogoVideoService {
    
    @Autowired private VideoRepository videoRepository;
    @Autowired private BuscarVideoService buscarVideoService;
    @Autowired private BuscarCategoriaService buscarCategoriaService;
    
    @Transactional
    public Video save(Video video) {

        Categoria categoriaEncontrada = buscarCategoriaService.buscar(video.getCategoria().getId());

        if (categoriaEncontrada.inativa()) {
            throw new NegocioExeption("video.nao.pode.ser.incluido.categoria.inativa");
        }

        video.setCategoria(categoriaEncontrada);

        return videoRepository.save(video);
    }

    @Transactional
    public void deleteById(Long videoId) {
        Video videoEncontrado = buscarVideoService.buscar(videoId);

        videoRepository.delete(videoEncontrado);
    }
 
}
