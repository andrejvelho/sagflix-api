package br.com.resultatec.sagflix.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.resultatec.sagflix.domain.model.Categoria;
import br.com.resultatec.sagflix.domain.model.Video;
import br.com.resultatec.sagflix.domain.repository.VideoRepository;

@Service
public class CatalogoVideoService {
    
    @Autowired private VideoRepository videoRepository;
    @Autowired private CatalogoCategoriaService catalogoCategoriaService;
    @Autowired private BuscarVideoService buscarVideoService;
    
    @Transactional
    public Video save(Video video) {

        Categoria categoria = catalogoCategoriaService.buscarOuFalhar(video.getCategoria().getId());

        video.setCategoria(categoria);

        return videoRepository.save(video);
    }

    @Transactional
    public void deleteById(Long videoId) {
        Video videoEncontrado = buscarVideoService.buscar(videoId);

        videoRepository.delete(videoEncontrado);
    }
 
}
