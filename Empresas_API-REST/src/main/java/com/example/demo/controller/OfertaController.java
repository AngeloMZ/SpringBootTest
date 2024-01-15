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

import com.example.demo.bean.Ofertas;
import com.example.demo.repository.OfertaRepository;
@RestController
@RequestMapping("/api")
public class OfertaController {

    @Autowired
    private OfertaRepository ofertaRepository;

    @GetMapping ("/ofertas")
    public Iterable<Ofertas> buscaTots() {
        return ofertaRepository.findAll();
    }

    @GetMapping("/ofertas/{id}")
    public ResponseEntity<Ofertas> buscaPerId(@PathVariable long id) {
        Optional<Ofertas> ofertaOptional = ofertaRepository.findById(id);

        return ofertaOptional.map(oferta -> ResponseEntity.ok().body(oferta)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/ofertas")
    public ResponseEntity<Ofertas> afegeix(@RequestBody Ofertas oferta) {
        Ofertas ofertaNueva = ofertaRepository.save(oferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaNueva);
    }

    @DeleteMapping("/ofertas/{id}")
    public ResponseEntity<Void> esborra(@PathVariable long id) {
        Optional<Ofertas> ofertaOptional = ofertaRepository.findById(id);

        if (ofertaOptional.isPresent()) {
            ofertaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ofertas/{id}")
    public ResponseEntity<Ofertas> modifica(@PathVariable long id, @RequestBody Ofertas ofertaModificada) {
        Optional<Ofertas> ofertaExistenteOptional = ofertaRepository.findById(id);

        if (ofertaExistenteOptional.isPresent()) {
            Ofertas ofertaExistente = ofertaExistenteOptional.get();
            ofertaExistente.setNom(ofertaModificada.getNom());
            ofertaExistente.setDescripcion(ofertaModificada.getDescripcion());
            Ofertas ofertaActualizada = ofertaRepository.save(ofertaExistente);
            return ResponseEntity.ok().body(ofertaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}