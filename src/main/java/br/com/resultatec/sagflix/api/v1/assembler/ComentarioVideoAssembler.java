package br.com.resultatec.sagflix.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.resultatec.sagflix.api.v1.model.representation.ComentarioVideoModel;
import br.com.resultatec.sagflix.domain.model.ComentarioVideo;

@Component
public class ComentarioVideoAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public ComentarioVideoModel toModel(ComentarioVideo comentarioVideo) {
        return modelMapper.map(comentarioVideo, ComentarioVideoModel.class);
    }

    public List<ComentarioVideoModel> toCollectionModel(List<ComentarioVideo> comentarios) {
        return comentarios
        .stream()
        .map(this::toModel)
        .collect(Collectors.toList());
    }
}
