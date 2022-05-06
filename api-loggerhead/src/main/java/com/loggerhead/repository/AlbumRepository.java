package com.loggerhead.repository;

import java.util.Optional;

import com.loggerhead.entity.Album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>{
    public Optional<Album> findByDescripcion (String descripcion);
    
}
