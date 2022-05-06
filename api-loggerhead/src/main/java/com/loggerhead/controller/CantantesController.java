package com.loggerhead.controller;

import java.util.List;

import javax.validation.Valid;

import com.loggerhead.dto.MessageResponse;
import com.loggerhead.entity.Cantantes;
import com.loggerhead.services.CantantesServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cantantes")
@CrossOrigin(origins = "http://localhost:4200")
public class CantantesController {
    @Autowired
    private CantantesServices cantantesServices;

//obtener datos
//not content no hay datos
    @GetMapping ("/getAll")
    public ResponseEntity <?> obtenerTodosLosCantantes(){
        List<Cantantes> cantantes = this.cantantesServices.getALLCantantes();

        if(cantantes.isEmpty())
            this.errorMessage("No hay cantantes para Mostrar");
            //return ResponseEntity.noContent().build();
        return new ResponseEntity<List<Cantantes>>(cantantes, HttpStatus.OK );
        //return ResponseEntity.ok(cantantes);
    }

    //enviar datos 'insertar datos en la database'
    @PostMapping(path = "/postdata")
    public ResponseEntity<Cantantes> registrarNewCantantes(@Valid @RequestBody Cantantes cantantes){
        Cantantes cantantes2 = this.cantantesServices.crearCantantes(cantantes);
        return ResponseEntity.ok(cantantes2);
    }

//se actualiza un dato espesifico
    @PutMapping(path = "/putdata")
    public ResponseEntity<Cantantes> actualizarDatos(@PathVariable (value = "authuser") Integer id_cantantes, @ RequestBody Cantantes cantantes){
        if(!this.cantantesServices.exixteID(id_cantantes)) return ResponseEntity.notFound().build();
        Cantantes cantantes2 = this.cantantesServices.getById(id_cantantes).get();
        cantantes2.setNombre(cantantes.getNombre());
        Cantantes cantantessafe = this.cantantesServices.actualizarCantantes(cantantes2);
        return ResponseEntity.ok(cantantessafe);
    }

//eliminar datos
    @DeleteMapping(path = "/deletedata")
    public ResponseEntity<?> eliminarDatos(@PathVariable (value = "eliminar") Integer id_cantantes){
        if(!this.cantantesServices.exixteID(id_cantantes)) return ResponseEntity.notFound().build();
        this.cantantesServices.eliminarCanciones(id_cantantes);
        return ResponseEntity.ok("Cantante eliminado correctamente");
    }

    private ResponseEntity<MessageResponse> errorMessage (String message) {
        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.BAD_REQUEST);
    }

}
