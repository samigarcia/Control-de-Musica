package com.loggerhead.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.loggerhead.entity.Canciones;
import com.loggerhead.repository.CancionesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CancionesServices {
    @Autowired
    private CancionesRepository cancionesRepository;

    public List<Canciones> getALLCanciones(){
        return this.cancionesRepository.findAll();
    }

    public Optional<Canciones> getByTitulo(String titulo){
        return this.cancionesRepository.findByTitulo(titulo);
    }

    public Optional<Canciones> getById(Integer id){
        return this.cancionesRepository.findById(id);
    }

    public Canciones crearCanciones (Canciones canciones){
        return this.cancionesRepository.save(canciones);
    }

    public Canciones actualizarCanciones (Canciones canciones){
        Canciones cancioneshp = this.cancionesRepository.findById(canciones.getId()).get();
        cancioneshp.setTitulo(canciones.getTitulo());
        cancioneshp.setGenero(canciones.getGenero());
        cancioneshp.setconpositor(canciones.getconpositor());
        return this.cancionesRepository.save(cancioneshp);
    }

    public Boolean existexID (Integer ID){
        return this.cancionesRepository.existsById(ID);
    }

    public void eliminarCanciones (Integer id){
        this.cancionesRepository.deleteById(id);
    }
}
