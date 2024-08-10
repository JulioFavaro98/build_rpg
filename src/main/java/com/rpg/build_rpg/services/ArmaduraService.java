package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.repositories.ArmaduraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArmaduraService {

    @Autowired
    private ArmaduraRepository armaduraRepository;

    public List<Armadura> getAllArmaduras() {
        return armaduraRepository.findAll();
    }

    public Optional<Armadura> getArmaduraById(UUID id) {
        Optional<Armadura> armadura = armaduraRepository.findById(id);
        return armadura;
    }

    public Armadura createArmadura(Armadura armadura) {
        if(armaduraRepository.findByNome(armadura.getNome()).isPresent()){
            throw new IllegalArgumentException("Já existe uma armadura com o nome " + armadura.getNome());
        }
        return armaduraRepository.save(armadura);
    }
}
