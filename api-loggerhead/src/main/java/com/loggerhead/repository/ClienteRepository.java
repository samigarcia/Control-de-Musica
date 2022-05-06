package com.loggerhead.repository;

import java.util.Optional;

import com.loggerhead.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Optional<Cliente> findByNombre (String nombre);
    //public Optional<Cliente> findByContraseña (String contraseña);
    public Optional<Cliente> findByCorreo (String correo);
}
