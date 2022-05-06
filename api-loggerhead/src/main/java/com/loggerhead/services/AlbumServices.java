package com.loggerhead.services;

import java.util.List;
import java.util.Optional;

import com.loggerhead.entity.Album;
import com.loggerhead.repository.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumServices {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        return this.albumRepository.findAll();
    }

    public Optional<Album> getById(Integer id){
        return this.albumRepository.findById(id);
    }

    public Optional<Album> getByDescription(String description) {
        return this.albumRepository.findByDescripcion(description);
    }

    public Album crearAlbum (Album album){
        return this.albumRepository.save(album);
    }

    public Album actualizarAlbum (Album album){
        Album albumdb = this.getById(album.getId()).get();
        albumdb.setDescripcion(album.getDescripcion());
        return this.albumRepository.save(albumdb);
    }

    public Boolean existexID (Integer ID){
        return this.albumRepository.existsById(ID);
    }

    public void eliminarAlbum (Integer id){
        this.albumRepository.deleteById(id);
    }
}
