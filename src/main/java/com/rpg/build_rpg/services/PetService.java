package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Pet;
import com.rpg.build_rpg.infra.exceptions.ItemAlreadyExistsException;
import com.rpg.build_rpg.infra.exceptions.ItemNotFoundException;
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

    public Pet getPetById(UUID id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElseThrow(() -> new ItemNotFoundException("Pet não encontrado! Id: " + id));
    }

    public Pet createPet(Pet pet) {
        if(petRepository.findByNome(pet.getNome()).isPresent()){
            throw new ItemAlreadyExistsException("Já existe um pet com o nome " + pet.getNome());
        }
        return petRepository.save(pet);
    }

    public Pet updatePet(UUID id, Pet petAtualizado) {
        Pet pet = getPetById(id);

        pet.setNome(petAtualizado.getNome());
        pet.setDescricao(petAtualizado.getDescricao());
        pet.setDano(petAtualizado.getDano());
        pet.setDefesa(petAtualizado.getDefesa());

        return petRepository.save(pet);
    }

    public void deletePet(UUID id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Pet não encontrado! Id: " + id);
        }
    }
}
