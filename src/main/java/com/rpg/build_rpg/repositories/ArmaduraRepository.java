package com.rpg.build_rpg.repositories;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Armadura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArmaduraRepository extends JpaRepository<Armadura, UUID> {
    Optional<Armadura> findByNome(String nome);
}
