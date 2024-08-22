package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.services.ArmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/arma")
public class ArmaController {

    @Autowired
    private ArmaService armaService;

    @GetMapping("/armas")
    public ResponseEntity<List<Arma>> getAllArmas() {
        List<Arma> armas = armaService.getAllArmas();
        return ResponseEntity.ok(armas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arma> getArmaById(@PathVariable UUID id) {
        Optional<Arma> arma = armaService.getArmaById(id);
        return ResponseEntity.ok(arma.get());
    }

    @PostMapping
    public ResponseEntity<Arma> createArma(@RequestBody Arma arma) {
        Arma novaArma = armaService.createArma(arma);
        return new ResponseEntity<>(novaArma, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arma> updateArma(@PathVariable UUID id, @RequestBody Arma arma) {
        Arma armaAtualizada = armaService.updateArma(id, arma);
        return ResponseEntity.ok(armaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArma(@PathVariable UUID id) {
        armaService.deleteArma(id);
        return ResponseEntity.noContent().build();

    }
}
