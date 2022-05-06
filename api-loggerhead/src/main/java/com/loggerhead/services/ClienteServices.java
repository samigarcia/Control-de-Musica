package com.loggerhead.services;

import java.util.List;
import java.util.Optional;

import com.loggerhead.entity.Cliente;
import com.loggerhead.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getALLCliente(){
        return this.clienteRepository.findAll();
    }
    
    public Optional<Cliente> getByNombre(String nombre){
        return this.clienteRepository.findByNombre(nombre);
    }

    public Optional<Cliente> getById(Integer id){
        return this.clienteRepository.findById(id);
    }

    //public Optional<Cliente> getByContraseña(String contraseña){
      //  return this.clienteRepository.findByContraseña(contraseña);
    //}

    public Cliente crearCliente (Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente (Cliente cliente){
        Cliente cliente2 = this.getById(cliente.getId()).get();
        cliente2.setNombre(cliente.getNombre());
        return this.clienteRepository.save(cliente2);
    }

    public Boolean existeId (Integer id){
       return this.clienteRepository.existsById(id);
    }

    public void eliminarCliente (Integer id){
        this.clienteRepository.deleteById(id);
    }


}