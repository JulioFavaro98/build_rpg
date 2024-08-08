package com.rpg.build_rpg.repositories;

import com.rpg.build_rpg.entities.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonagemRepository extends JpaRepository<Personagem, UUID> {
    Optional<Personagem> findByNome(String nome);
}
