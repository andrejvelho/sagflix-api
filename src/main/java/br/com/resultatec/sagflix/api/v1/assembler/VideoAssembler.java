package br.com.resultatec.sagflix.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.resultatec.sagflix.api.v1.model.input.VideoInput;
import br.com.resultatec.sagflix.api.v1.model.representation.VideoModel;
import br.com.resultatec.sagflix.domain.model.Video;

@Component
public class VideoAssembler {
    
    @Autowired
    private ModelMapper modelMapper;

    public VideoModel toModel(Video video) {
        return modelMapper.map(video, VideoModel.class);
    }

    public List<VideoModel> toCollectionModel(List<Video> videos) {
        return videos
        .stream()
        .map(this::toModel)
        .collect(Collectors.toList());
    }

    public Video toEntity(VideoInput videoInput) {
        return modelMapper.map(videoInput, Video.class);
    }
}
