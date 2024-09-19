package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Pet;
import com.rpg.build_rpg.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(){
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id){
        Optional<Pet> pet = petService.getPetById(id);
        return ResponseEntity.ok(pet.get());
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet){
        Pet novoPet = petService.createPet(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@Valid @RequestBody Pet pet, @PathVariable UUID id) {
        Pet petAtualizado = petService.updatePet(id, pet);
        return ResponseEntity.ok(petAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable UUID id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
