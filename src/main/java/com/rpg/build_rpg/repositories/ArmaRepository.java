package com.rpg.build_rpg.repositories;

import com.rpg.build_rpg.entities.Arma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArmaRepository extends JpaRepository<Arma, UUID> {
    Optional<Arma> findByNome(String nome);
}
