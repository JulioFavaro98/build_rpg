package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.DTOs.PersonagemResponseDTO;
import com.rpg.build_rpg.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @GetMapping("/personagens")
    public ResponseEntity<List<PersonagemResponseDTO>> getAllPersonagens() {
        List<PersonagemResponseDTO> personagens = personagemService.getAllPersonagens();
        return new ResponseEntity<>(personagens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemResponseDTO> getPersonagemById(@PathVariable UUID id) {
        Optional<PersonagemResponseDTO> personagem = personagemService.getPersonagemById(id);
        return personagem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPersonagem(@RequestBody PersonagemResponseDTO personagemDTO) {
        try {
            PersonagemResponseDTO savedPersonagem = personagemService.createPersonagem(personagemDTO);
            return new ResponseEntity<>(savedPersonagem, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemResponseDTO> updatePersonagem(@PathVariable UUID id, @RequestBody PersonagemResponseDTO personagemDTO) {
        PersonagemResponseDTO updatedPersonagem = personagemService.updatePersonagem(id, personagemDTO);
        if (updatedPersonagem == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(updatedPersonagem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonagem(@PathVariable UUID id) {
        if (!personagemService.deletePersonagem(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
