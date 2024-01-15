package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Empresa;
import com.example.demo.repository.EmpresaRepository;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/empresas")
    public Iterable<Empresa> buscaTots() {
        return empresaRepository.findAll();
    }
    

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> buscaPerId(@PathVariable long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        return empresaOptional.map(empresa -> ResponseEntity.ok().body(empresa)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/empresa")
    public ResponseEntity<Empresa> afegeix(@RequestBody Empresa empresa) {
        // Asegurémonos de establecer las ofertas de la empresa a null antes de guardarla
        empresa.setOfertas(null);
        
        Empresa empresaNueva = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaNueva);
    }

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<Void> esborra(@PathVariable long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<Empresa> modifica(@PathVariable long id, @RequestBody Empresa empresaModificada) {
        Optional<Empresa> empresaExistenteOptional = empresaRepository.findById(id);

        if (empresaExistenteOptional.isPresent()) {
            Empresa empresaExistente = empresaExistenteOptional.get();
            empresaExistente.setNom(empresaModificada.getNom());
            empresaExistente.setDescripcion(empresaModificada.getDescripcion());
            // Puedes actualizar otros campos según tus necesidades

            // Asegurémonos de establecer las ofertas de la empresa a null antes de guardarla
            empresaExistente.setOfertas(null);

            Empresa empresaActualizada = empresaRepository.save(empresaExistente);
            return ResponseEntity.ok().body(empresaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
