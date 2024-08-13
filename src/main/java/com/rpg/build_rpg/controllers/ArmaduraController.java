package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.services.ArmaduraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/armadura")
public class ArmaduraController {

    @Autowired
    private ArmaduraService armaduraService;

    @GetMapping("/armaduras")
    public ResponseEntity<List<Armadura>> getAllArmaduras(){
        List<Armadura> armaduras = armaduraService.getAllArmaduras();
        return new ResponseEntity<>(armaduras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Armadura> getArmaduraById(@PathVariable UUID id){
        Optional<Armadura> armadura = armaduraService.getArmaduraById(id);
        return armadura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createArmadura(@RequestBody Armadura armadura){
        try {
            Armadura novaArmadura = armaduraService.createArmadura(armadura);
            return new ResponseEntity<>(novaArmadura, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArmadura(@PathVariable UUID id, @RequestBody Armadura armadura){
        try {
            armaduraService.updateArmadura(id, armadura);
            return ResponseEntity.ok("Armadura Atualizada com Sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar armadura: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArmadura(@PathVariable UUID id){
        try {
            armaduraService.deleteArmadura(id);
            return ResponseEntity.ok("Armadura deletada com SUCESSO!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar armadura: " + e.getMessage());
        }
    }
}
