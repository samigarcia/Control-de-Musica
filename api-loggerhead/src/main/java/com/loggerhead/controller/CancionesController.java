package com.loggerhead.controller;

import java.util.List;

import javax.validation.Valid;

import com.loggerhead.dto.MessageResponse;
import com.loggerhead.entity.Canciones;
import com.loggerhead.services.CancionesServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/canciones")
@CrossOrigin(origins = "http://localhost:4200")
public class CancionesController {
    @Autowired
    private CancionesServices cancionesServices;

    //obtener todos los datos
    //not content no hay datos
    @GetMapping ("/getAll")
    public ResponseEntity <?> ObtenerTodosLasCanciones(){
        List<Canciones> canciones = this.cancionesServices.getALLCanciones();

        if(canciones.isEmpty())
            this.errorMessage("No hay canciones para mostrar");
            //return ResponseEntity.noContent().build();
          
         return new ResponseEntity<List<Canciones>>(canciones, HttpStatus.OK);   
        //return ResponseEntity.ok(canciones);
    }

    //se crea un dato espesifico
    @PostMapping(path = "/postdata")
    public ResponseEntity<Canciones> registrarNewCanciones(@Valid @RequestBody Canciones canciones){
        Canciones canciones2 = this.cancionesServices.crearCanciones(canciones);
        return ResponseEntity.ok(canciones2);
    } 

    //se actualiza un dato espesifico
    @PutMapping(path = "/putdata/{id}")
    public ResponseEntity<?> actualizarDatos(@PathVariable (value = "id") Integer id_canciones, @RequestBody Canciones canciones){
        if(!this.cancionesServices.existexID(id_canciones)) 
            return this.errorMessage("Cancion/Canciones no encontra/s");

        Canciones canciones2 = this.cancionesServices.getById(id_canciones).get();
        canciones2.setTitulo(canciones.getTitulo());
        canciones2.setconpositor(canciones.getconpositor());
        canciones2.setGenero(canciones.getGenero());
        Canciones cancionessafe = this.cancionesServices.actualizarCanciones(canciones2);
        return new ResponseEntity<Canciones>(cancionessafe, HttpStatus.OK);
        //return ResponseEntity.ok(cancionessafe);
    }

    //eliminar datos
    @DeleteMapping(path = "/deletedata/{id}")
    public ResponseEntity<?> eliminarCanciones (@PathVariable (value = "id") Integer id_canciones){
        if(!this.cancionesServices.existexID(id_canciones) ) 
            return this.errorMessage("cancion no encontrada");

        this.cancionesServices.eliminarCanciones(id_canciones);
        return new ResponseEntity<MessageResponse>(new MessageResponse("Cancion eliminada correctamente"), HttpStatus.OK);
    }

    private ResponseEntity<MessageResponse> errorMessage(String message) {
        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.BAD_REQUEST);
    }
}


