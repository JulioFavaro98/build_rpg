package com.rpg.build_rpg.repositories;

import com.rpg.build_rpg.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
    Optional<Pet> findByNome(String nome);
}
