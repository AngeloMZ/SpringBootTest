package com.example.demo.repository;

import java.util.List;
//hola
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	List<Empresa> findAllById(long id);
    List<Empresa> findAllByNom(String nom);
    List<Empresa> findAllByDescripcion(String descripcion);
}
