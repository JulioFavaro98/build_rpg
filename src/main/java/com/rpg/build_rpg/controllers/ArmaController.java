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
        return new ResponseEntity<>(armas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arma> getArmaById(@PathVariable UUID id) {
        Optional<Arma> arma = armaService.getArmaById(id);
        return arma.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createArma(@RequestBody Arma arma) {
        try {
            Arma novaArma = armaService.createArma(arma);
            return new ResponseEntity<>(novaArma, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArma(@PathVariable UUID id, @RequestBody Arma arma) {
        try {
            armaService.updateArma(id, arma);
            return ResponseEntity.ok("Arma Atualizada com Sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar arma: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArma(@PathVariable UUID id) {
        try {
            armaService.deleteArma(id);
            return ResponseEntity.ok("Arma deletada com SUCESSO!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar arma: " + e.getMessage());
        }
    }
}
