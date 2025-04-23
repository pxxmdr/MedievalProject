package com.marketSkyrim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marketSkyrim.model.Classe;
import com.marketSkyrim.model.Personagem;
import com.marketSkyrim.repository.PersonagemRepository;

@RestController
@RequestMapping("/personagem")
@CrossOrigin(origins = "*")
public class PersonagemController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostMapping
    public ResponseEntity<Personagem> createPersonagem(@RequestBody Personagem personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personagemRepository.save(personagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> getPersonagemById(@PathVariable Long id) {
        return personagemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }

    @GetMapping
    public ResponseEntity<Page<Personagem>> getAllPersonagens(Pageable pageable) {
        return ResponseEntity.ok(personagemRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> updatePersonagem(@PathVariable Long id, @RequestBody Personagem personagemDetails) {
        return personagemRepository.findById(id)
                .map(personagem -> {
                    personagem.setNome(personagemDetails.getNome());
                    personagem.setNivel(personagemDetails.getNivel());
                    personagem.setMoeda(personagemDetails.getMoeda());
                    personagem.setClasse(personagemDetails.getClasse());
                    return ResponseEntity.ok(personagemRepository.save(personagem));
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonagem(@PathVariable Long id) {
        if (!personagemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado");
        }
        personagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/buscar/nome")
    public ResponseEntity<List<Personagem>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(personagemRepository.findByNomeIgnoreCase(nome));
    }

    @GetMapping("/buscar/classe")
    public ResponseEntity<List<Personagem>> buscarPorClasse(@RequestParam Classe classe) {
        return ResponseEntity.ok(personagemRepository.findByClasse(classe));
    }
}
