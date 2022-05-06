package com.loggerhead.repository;

import java.util.Optional;

import com.loggerhead.entity.Canciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionesRepository extends JpaRepository<Canciones, Integer>{
    public Optional<Canciones> findByTitulo (String titulo);
    public Optional<Canciones> findByConpositor (String conpositor);
    public Optional<Canciones> findByGenero (String genero);
    
}
