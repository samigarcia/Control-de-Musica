package com.loggerhead.services;

import java.util.List;
import java.util.Optional;

import com.loggerhead.entity.Cantantes;
import com.loggerhead.repository.CantantesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CantantesServices {
    @Autowired
    private CantantesRepository cantantesRepository;

    public List<Cantantes> getALLCantantes(){
        return this.cantantesRepository.findAll();
    }

    public Optional<Cantantes> getByNombre(String nombre){
        return this.cantantesRepository.findByNombre(nombre);
    }

    public Optional<Cantantes> getById(int id){
        return this.cantantesRepository.findById(id);
    }

    public Cantantes crearCantantes(Cantantes cantantes){
        return this.cantantesRepository.save(cantantes);
    }

    public Cantantes actualizarCantantes (Cantantes cantantes){
        Cantantes cantantes2 = this.getById(cantantes.getId()).get();
        cantantes2.setNombre(cantantes.getNombre());
        return this.cantantesRepository.save(cantantes2);
    }

    public Boolean exixteID (Integer id){
        return this.cantantesRepository.existsById(id);
    }

    public void eliminarCanciones(Integer id) {
        this.cantantesRepository.deleteById(id);
    }
}
