package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.repositories.ArmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmaService {

    @Autowired
    private ArmaRepository armaRepository;


    public List<Arma> getAllArmas() {
        return armaRepository.findAll();
    }

    public Arma createArma(Arma arma) {
        if(armaRepository.findByNome(arma.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma arma com o nome " + arma.getNome());
        }
        return armaRepository.save(arma);
    }
}
