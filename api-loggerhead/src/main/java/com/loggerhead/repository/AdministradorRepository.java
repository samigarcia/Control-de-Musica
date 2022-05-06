package com.loggerhead.repository;

import java.util.Optional;

import com.loggerhead.entity.Administrador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    public Optional<Administrador> findByNombre (String nombre);
    public Optional<Administrador> findByContraseña (String contraseña);
}
