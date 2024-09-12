package com.rpg.build_rpg.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O NOME do PET é OBRIGATÓRIO.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "A DESCRIÇÃO do PET é OBRIGATÓRIA.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "O DANO do PET é OBRIGATÓRIO.")
    private Integer dano;

    @Column(nullable = false)
    @NotNull(message = "A DEFESA do PET é OBRIGATÓRIA.")
    private Integer defesa;
}
