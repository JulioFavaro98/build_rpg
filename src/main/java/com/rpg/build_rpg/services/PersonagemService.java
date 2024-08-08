package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.DTOs.PersonagemResponseDTO;
import com.rpg.build_rpg.entities.Personagem;
import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.repositories.PersonagemRepository;
import com.rpg.build_rpg.repositories.ArmaRepository;
import com.rpg.build_rpg.entities.enums.Classe;
import com.rpg.build_rpg.entities.enums.Raca;
import com.rpg.build_rpg.entities.enums.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ArmaRepository armaRepository;

    public List<PersonagemResponseDTO> getAllPersonagens() {
        return personagemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonagemResponseDTO createPersonagem(PersonagemResponseDTO personagemDTO) {
        Personagem personagem = convertToEntity(personagemDTO);
        if(personagemRepository.findByNome(personagem.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe um personagem com o nome " + personagem.getNome());
        }
        Personagem savedPersonagem = personagemRepository.save(personagem);
        return convertToDTO(savedPersonagem);
    }

    public Optional<PersonagemResponseDTO> getPersonagemById(UUID id) {
        Optional<Personagem> personagem = personagemRepository.findById(id);
        return personagem.map(this::convertToDTO);
    }

    public PersonagemResponseDTO updatePersonagem(UUID id, PersonagemResponseDTO personagemDTO) {
        if (!personagemRepository.existsById(id)) {
            return null;
        }
        Personagem personagem = convertToEntity(personagemDTO);
        personagem.setId(id);
        Personagem updatedPersonagem = personagemRepository.save(personagem);
        return convertToDTO(updatedPersonagem);
    }

    public boolean deletePersonagem(UUID id) {
        if (!personagemRepository.existsById(id)) {
            return false;
        }
        personagemRepository.deleteById(id);
        return true;
    }

    private PersonagemResponseDTO convertToDTO(Personagem personagem) {
        PersonagemResponseDTO dto = new PersonagemResponseDTO();
        dto.setId(personagem.getId());
        dto.setNome(personagem.getNome());
        dto.setSexo(personagem.getSexo().name());
        dto.setRaca(personagem.getRaca().name());
        dto.setClasse(personagem.getClasse().name());
        dto.setArmaPrincipal(personagem.getArmaPrincipal() != null ? personagem.getArmaPrincipal().getNome() : null);
        dto.setArmaSecundaria(personagem.getArmaSecundaria() != null ? personagem.getArmaSecundaria().getNome() : null);
        dto.setArmadura(personagem.getArmadura());
        dto.setReligiao(personagem.getReligiao());
        dto.setPet(personagem.getPet());
        return dto;
    }

    private Personagem convertToEntity(PersonagemResponseDTO dto) {
        Personagem personagem = new Personagem();
        personagem.setNome(dto.getNome());
        personagem.setSexo(Sexo.valueOf(dto.getSexo()));
        personagem.setRaca(Raca.valueOf(dto.getRaca()));
        personagem.setClasse(Classe.valueOf(dto.getClasse()));

        if (dto.getArmaPrincipal() != null && !dto.getArmaPrincipal().isEmpty()) {
            Optional<Arma> armaPrincipal = armaRepository.findById(UUID.fromString(dto.getArmaPrincipal()));
            if (armaPrincipal.isPresent()) personagem.setArmaPrincipal(armaPrincipal.get());
        }

        if (dto.getArmaSecundaria() != null && !dto.getArmaSecundaria().isEmpty()) {
            Optional<Arma> armaSecundaria = armaRepository.findById(UUID.fromString(dto.getArmaSecundaria()));
            if (armaSecundaria.isPresent()) personagem.setArmaSecundaria(armaSecundaria.get());
        }

        personagem.setArmadura(dto.getArmadura());
        personagem.setReligiao(dto.getReligiao());
        personagem.setPet(dto.getPet());
        return personagem;
    }
}
