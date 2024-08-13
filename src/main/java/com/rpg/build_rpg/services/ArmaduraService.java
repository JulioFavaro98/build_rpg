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

    public Armadura updateArmadura(UUID id, Armadura armaduraAtualizada) {
        try {
            Armadura armadura = getArmaduraById(id)
                    .orElseThrow(()-> new IllegalArgumentException("Armadura não encontrada!"));
            armadura.setNome(armaduraAtualizada.getNome());
            armadura.setTipo(armaduraAtualizada.getTipo());
            armadura.setDescricao(armaduraAtualizada.getDescricao());
            armadura.setDefesa(armaduraAtualizada.getDefesa());

            return armaduraRepository.save(armadura);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a armadura: " + e.getMessage());
        }
    }

    public void deleteArmadura(UUID id){
        if(armaduraRepository.existsById(id)){
            armaduraRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Armadura não encontrada com o id fornecido.");
        }
    }
}
