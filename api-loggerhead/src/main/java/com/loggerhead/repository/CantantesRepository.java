package com.loggerhead.repository;

import java.util.Optional;

import com.loggerhead.entity.Cantantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantantesRepository extends JpaRepository<Cantantes, Integer> {
    public Optional<Cantantes> findByNombre (String nombre); 
    public Optional<Cantantes> findByNacionalidad (String nacionalidad);
}
