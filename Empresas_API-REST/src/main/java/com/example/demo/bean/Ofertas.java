package com.example.demo.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

@Entity
public class Ofertas {
	@Id
	private long id;

	@Column(nullable = false, unique = true)
	private String nom;

	@Column(nullable = false)
	private String descripcion;

	@JsonIgnore
    @ManyToOne
    private Empresa empresa;

    // Constructor vacío
    public Ofertas() {
    }

    // Constructor con parámetros
    public Ofertas(long id, String nom, String descripcion, Empresa empresa) {
        this.id = id;
        this.nom = nom;
        this.descripcion = descripcion;
        this.empresa = empresa;
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion ) {
        this.descripcion = descripcion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
