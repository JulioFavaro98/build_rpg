package com.rpg.build_rpg.services;

import com.rpg.build_rpg.entities.Armadura;
import com.rpg.build_rpg.entities.DTOs.PersonagemResponseDTO;
import com.rpg.build_rpg.entities.Personagem;
import com.rpg.build_rpg.entities.Arma;
import com.rpg.build_rpg.entities.Pet;
import com.rpg.build_rpg.infra.exceptions.ItemAlreadyExistsException;
import com.rpg.build_rpg.infra.exceptions.ItemNotFoundException;
import com.rpg.build_rpg.repositories.ArmaduraRepository;
import com.rpg.build_rpg.repositories.PersonagemRepository;
import com.rpg.build_rpg.repositories.ArmaRepository;
import com.rpg.build_rpg.entities.enums.Classe;
import com.rpg.build_rpg.entities.enums.Raca;
import com.rpg.build_rpg.entities.enums.Sexo;
import com.rpg.build_rpg.repositories.PetRepository;
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

    @Autowired
    private ArmaduraRepository armaduraRepository;

    @Autowired
    private PetRepository petRepository;

    public List<PersonagemResponseDTO> getAllPersonagens() {
        return personagemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Personagem getPersonagemById(UUID id) {
        Optional<Personagem> personagem = personagemRepository.findById(id);
        return personagem.orElseThrow(() -> new ItemNotFoundException("Personagem não encontrado! Id: " + id));
    }

    public PersonagemResponseDTO createPersonagem(PersonagemResponseDTO personagemDTO) {
        Personagem personagem = convertToEntity(personagemDTO);
        if(personagemRepository.findByNome(personagem.getNome()).isPresent()) {
            throw new ItemAlreadyExistsException("Já existe um personagem com o nome " + personagem.getNome());
        }
        Personagem savedPersonagem = personagemRepository.save(personagem);
        return convertToDTO(savedPersonagem);
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

    public void deletePersonagem(UUID id) {
        if(personagemRepository.existsById(id)) {
            personagemRepository.deleteById(id);
        } else {
            throw new ItemNotFoundException("Personagem não encontrado! Id: " + id);
        }
    }

    public PersonagemResponseDTO convertToDTO(Personagem personagem) {
        PersonagemResponseDTO dto = new PersonagemResponseDTO();
        dto.setNome(personagem.getNome());
        dto.setSexo(personagem.getSexo().name());
        dto.setRaca(personagem.getRaca().name());
        dto.setClasse(personagem.getClasse().name());
        dto.setArmaPrincipal(personagem.getArmaPrincipal() != null ? personagem.getArmaPrincipal().getNome() : null);
        dto.setArmaSecundaria(personagem.getArmaSecundaria() != null ? personagem.getArmaSecundaria().getNome() : null);
        dto.setArmadura(personagem.getArmadura() != null ? personagem.getArmadura().getNome() : null);
        dto.setPet(personagem.getPet() != null ? personagem.getPet().getNome() : null);
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

        if (dto.getArmadura() != null && !dto.getArmadura().isEmpty()) {
            Optional<Armadura> armadura = armaduraRepository.findById(UUID.fromString(dto.getArmadura()));
            if (armadura.isPresent()) personagem.setArmadura(armadura.get());
        }

        if (dto.getPet() != null && !dto.getPet().isEmpty()) {
            Optional<Pet> pet = petRepository.findById(UUID.fromString(dto.getPet()));
            if (pet.isPresent()) personagem.setPet(pet.get());
        }

        return personagem;
    }
}
