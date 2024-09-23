package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.infra.exceptions.ItemAlreadyExistsException;
import com.rpg.build_rpg.infra.exceptions.ItemNotFoundException;
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

    public Armadura getArmaduraById(UUID id) {
        Optional<Armadura> armadura = armaduraRepository.findById(id);
        return armadura.orElseThrow(() -> new ItemNotFoundException("Armadura não encontrada! Id: " + id));
    }

    public Armadura createArmadura(Armadura armadura) {
        if(armaduraRepository.findByNome(armadura.getNome()).isPresent()) {
            throw new ItemAlreadyExistsException("Já existe uma armadura com o nome " + armadura.getNome());
        }
        return armaduraRepository.save(armadura);
    }

    public Armadura updateArmadura(UUID id, Armadura armaduraAtualizada) {
        Armadura armadura = getArmaduraById(id);

        armadura.setNome(armaduraAtualizada.getNome());
        armadura.setTipo(armaduraAtualizada.getTipo());
        armadura.setDescricao(armaduraAtualizada.getDescricao());
        armadura.setDefesa(armaduraAtualizada.getDefesa());

        return armaduraRepository.save(armadura);
    }

    public void deleteArmadura(UUID id){
        if(armaduraRepository.existsById(id)) {
            armaduraRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Armadura não encontrada! Id: " + id);
        }
    }
}
