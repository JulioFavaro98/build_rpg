package com.rpg.build_rpg.entities.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PersonagemResponseDTO {

    @NotNull(message = "O NOME do PERSONAGEM é OBRIGATÓRIO.")
    private String nome;

    @NotNull(message = "O SEXO do PERSONAGEM é OBRIGATÓRIO.")
    private String sexo;

    @NotNull(message = "A RAÇA do PERSONAGEM é OBRIGATÓRIA.")
    private String raca;

    @NotNull(message = "A CLASSE do PERSONAGEM é OBRIGATÓRIA.")
    private String classe;
    private String armaPrincipal;
    private String armaSecundaria;
    private String armadura;
    private String pet;
}
