package com.loggerhead.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Canciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String conpositor;
    private String titulo;
    private String genero;
    
    public Canciones() {
    }

    public Canciones(String conpositor, int id, String titulo, String genero) {
        this.conpositor = conpositor;
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
    }

    public String getconpositor() {
        return conpositor;
    }

    public void setconpositor(String conpositor) {
        this.conpositor = conpositor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
}
