package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Armadura;
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

    public Optional<Arma> getArmaById(UUID id) {
        Optional<Arma> arma = armaRepository.findById(id);
        return arma;
    }

    public Arma createArma(Arma arma) {
        if(armaRepository.findByNome(arma.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma arma com o nome " + arma.getNome());
        }
        return armaRepository.save(arma);
    }

    public Arma updateArma(UUID id, Arma armaAtualizada) {
        try {
            Arma arma = getArmaById(id)
                    .orElseThrow(()-> new IllegalArgumentException("Arma não encontrada!"));
            arma.setNome(armaAtualizada.getNome());
            arma.setTipo(armaAtualizada.getTipo());
            arma.setDescricao(armaAtualizada.getDescricao());
            arma.setDano(armaAtualizada.getDano());
            arma.setNegacaoDano(armaAtualizada.getNegacaoDano());

            return armaRepository.save(arma);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a arma: " + e.getMessage());
        }
    }

    public void deleteArma(UUID id) {
        if (armaRepository.existsById(id)) {
            armaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Arma não encontrada com o id fornecido.");
        }
    }
}
