package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.services.ArmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> createArma(@RequestBody Arma arma) {
        try {
            Arma novaArma = armaService.createArma(arma);
            return new ResponseEntity<>(novaArma, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
