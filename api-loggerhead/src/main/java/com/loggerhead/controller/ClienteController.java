package com.loggerhead.controller;

import java.util.List;

import javax.validation.Valid;

import com.loggerhead.entity.Cliente;
import com.loggerhead.services.ClienteServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/cliente")
public class ClienteController {
    @Autowired
    private ClienteServices clienteService;
//obtener datos
//not content no hay datos
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes(){
        List<Cliente> clientes = this.clienteService.getALLCliente();

        if(clientes.isEmpty()) 
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(clientes);
    }

//enviar datos 'insertar datos en la database'
    @PostMapping(path = "/postdata")
    public ResponseEntity<Cliente> registrarNewCliente(@Valid @RequestBody Cliente cliente){
        Cliente cliente2 = this.clienteService.crearCliente(cliente);
        return ResponseEntity.ok(cliente2);
    } 

//se actualiza un dato espesifico
    @PutMapping(path = "/putdata")
    public ResponseEntity<Cliente> actualizarDatos(@PathVariable (value = "authuser") Integer id_cliente, @RequestBody Cliente cliente){
        if(!this.clienteService.existeId(id_cliente)) return ResponseEntity.notFound().build();
        Cliente cliente2 = this.clienteService.getById(id_cliente).get();
        cliente2.setNombre(cliente.getNombre());
        Cliente clientesafe = this.clienteService.actualizarCliente(cliente2);
        return ResponseEntity.ok(clientesafe);
    }

//eliminar datos
    @DeleteMapping(path = "/deletedata")
    public ResponseEntity<?> eliminarDatos (@PathVariable (value = "eliminar") Integer id_cliente){
        if(!this.clienteService.existeId(id_cliente)) return ResponseEntity.notFound().build();
        this.clienteService.eliminarCliente(id_cliente);
        return ResponseEntity.ok("cliente eliminado correctamente");
    } 
}
