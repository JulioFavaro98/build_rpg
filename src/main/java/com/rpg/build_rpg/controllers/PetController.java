package com.rpg.build_rpg.controllers;

import com.rpg.build_rpg.entities.Pet;
import com.rpg.build_rpg.services.PetService;
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
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id){
        Optional<Pet> pet = petService.findPetById(id);
        return pet.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody Pet pet){
        try {
            Pet novoPet = petService.createPet(pet);
            return new ResponseEntity<>(novoPet, HttpStatus.CREATED);
        } catch (IllegalArgumentException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
