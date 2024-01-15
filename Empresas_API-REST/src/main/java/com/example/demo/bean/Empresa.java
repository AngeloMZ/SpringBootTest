package com.example.demo.bean;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Empresa {
	@Id 
	private long id;

	@Column(nullable = false, unique = true)
	private String nom;

	@Column(nullable = false)
	private String descripcion;
	

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ofertas> ofertas;//list
    
	// Constructor vacio
    public Empresa() {
    }

    // Constructor con parámetros
    public Empresa(long id, String nom, String descripcion) {
        this.id = id;
        this.nom = nom;
        this.descripcion = descripcion;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<Ofertas> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Ofertas> ofertas) {
        this.ofertas = ofertas;
    }

}
