package com.loggerhead.controller;

import java.util.List;

import javax.validation.Valid;

import com.loggerhead.dto.MessageResponse;
import com.loggerhead.entity.Album;
import com.loggerhead.services.AlbumServices;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/album")
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {

    @Autowired
    private AlbumServices albumService;

    // obtener datos
    // not content no hay datos
    @GetMapping(path = "/getAll")
    public ResponseEntity<?> obtenerTodosLosAlbums() {
        List<Album> albums = this.albumService.getAllAlbums();

        if (albums.isEmpty())
            this.errorMessage("No hay albumes para mostrar");

        return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
    }

//enviar datos 'insertar datos en la database'    
    @GetMapping(path = "/verAlbum/{id}")
    public ResponseEntity<?> verAlbum(@PathVariable(value = "id") Integer id) {
        Album album = this.albumService.getById(id).orElse(null);
        if (album == null)
            return this.errorMessage("Album no encontrado");

        return new ResponseEntity<Album>(album, HttpStatus.OK);
    }


//se crea un dato espesifico
    @PostMapping(path = "/postdata")
    public ResponseEntity<Album> registrarNewAlbum(@Valid @RequestBody Album album) {
        Album albumdb = this.albumService.crearAlbum(album);
        return ResponseEntity.ok(albumdb);
    }


// se actualiza un dato espesifico
    @PutMapping(path = "/putdata/{id}")
    public ResponseEntity<?> actualizarDatos(@PathVariable(value = "id") Integer id_album, @RequestBody Album album) {
        if (!this.albumService.existexID(id_album))
            return this.errorMessage("Album no encontrado");
            
        Album albumth = this.albumService.getById(id_album).get();
        albumth.setDescripcion(album.getDescripcion());
        Album albumsafe = this.albumService.actualizarAlbum(albumth);
        return new ResponseEntity<Album>(albumsafe, HttpStatus.OK);
    }

    // eliminar datos
    @DeleteMapping(path = "/deletedata/{id}")
    public ResponseEntity<?> eliminardatos(@PathVariable(value = "id") Integer id_album) {
        if (!this.albumService.existexID(id_album))
            return this.errorMessage("Album no encontrado");
        this.albumService.eliminarAlbum(id_album);
        return new ResponseEntity<MessageResponse>(new MessageResponse("Album eliminado correctamente"), HttpStatus.OK);
    }

    // Envia mensajes de respuesta cuando ocurre un error.
    // Se utiliza principalmente porque el front necesita un objeto en formato json
    // para ser procesado.
    private ResponseEntity<MessageResponse> errorMessage(String message) {
        return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.BAD_REQUEST);
    }

    // NOTA: El signo de admiración ? se utiliza para decirle a ResponseEntity que
    // el tipo de retorno no es especifico, por lo cual puede enviar diferentes
    // tipos de datos
}
