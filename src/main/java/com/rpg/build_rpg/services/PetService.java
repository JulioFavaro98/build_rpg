package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Pet;
import com.rpg.build_rpg.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet) {
        if(petRepository.findByNome(pet.getNome()).isPresent()){
            throw new IllegalArgumentException("Já existe um pet com o nome " + pet.getNome());
        }
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(UUID id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet;
    }
}
