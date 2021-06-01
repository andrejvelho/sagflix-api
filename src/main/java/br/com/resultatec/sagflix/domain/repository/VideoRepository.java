package br.com.resultatec.sagflix.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.resultatec.sagflix.domain.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    
}
