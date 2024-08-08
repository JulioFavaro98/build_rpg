package com.rpg.build_rpg.entities.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PersonagemResponseDTO {

    private UUID id;
    private String nome;
    private String sexo;
    private String raca;
    private String classe;
    private String armaPrincipal;
    private String armaSecundaria;
    private String armadura;
    private String religiao;
    private String pet;
}
