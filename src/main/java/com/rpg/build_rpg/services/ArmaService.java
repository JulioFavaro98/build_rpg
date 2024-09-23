package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.infra.exceptions.ItemAlreadyExistsException;
import com.rpg.build_rpg.infra.exceptions.ItemNotFoundException;
import com.rpg.build_rpg.repositories.ArmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArmaService {

    @Autowired
    private ArmaRepository armaRepository;


    public List<Arma> getAllArmas() {
        return armaRepository.findAll();
    }

    public Arma getArmaById(UUID id) {
        Optional<Arma> arma = armaRepository.findById(id);
        return arma.orElseThrow(() -> new ItemNotFoundException("Arma não encontrada! Id: " + id));
    }

    public Arma createArma(Arma arma) {
        if(armaRepository.findByNome(arma.getNome()).isPresent()) {
            throw new ItemAlreadyExistsException("Já existe uma arma com o nome " + arma.getNome());
        }
        return armaRepository.save(arma);
    }

    public Arma updateArma(UUID id, Arma armaAtualizada) {
        Arma arma = getArmaById(id);

        arma.setNome(armaAtualizada.getNome());
        arma.setTipo(armaAtualizada.getTipo());
        arma.setDescricao(armaAtualizada.getDescricao());
        arma.setDano(armaAtualizada.getDano());
        arma.setNegacaoDano(armaAtualizada.getNegacaoDano());

        return armaRepository.save(arma);

    }

    public void deleteArma(UUID id) {
        if(armaRepository.existsById(id)) {
            armaRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Arma não encontrada! Id: " + id);
        }
    }
}
