package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.services.ArmaduraService;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(armaduras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Armadura> getArmaduraById(@PathVariable UUID id){
        Armadura armadura = armaduraService.getArmaduraById(id);
        return ResponseEntity.ok(armadura);
    }

    @PostMapping
    public ResponseEntity<Armadura> createArmadura(@Valid @RequestBody Armadura armadura){
        Armadura novaArmadura = armaduraService.createArmadura(armadura);
        return new ResponseEntity<>(novaArmadura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Armadura> updateArmadura(@Valid @RequestBody Armadura armadura, @PathVariable UUID id){
        Armadura armaduraAtualizada = armaduraService.updateArmadura(id, armadura);
        return ResponseEntity.ok(armaduraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArmadura(@PathVariable UUID id){
        armaduraService.deleteArmadura(id);
        return ResponseEntity.noContent().build();
    }
}
