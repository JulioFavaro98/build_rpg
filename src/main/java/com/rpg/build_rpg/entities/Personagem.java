package com.rpg.build_rpg.entities;

import com.rpg.build_rpg.entities.enums.Classe;
import com.rpg.build_rpg.entities.enums.Raca;
import com.rpg.build_rpg.entities.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "personagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O NOME do PERSONAGEM é OBRIGATÓRIO.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O SEXO do PERSONAGEM é OBRIGATÓRIO.")
    private Sexo sexo;

    @Column(nullable = false)
    @NotNull(message = "A RAÇA do PERSONAGEM é OBRIGATÓRIA.")
    private Raca raca;

    @Column(nullable = false)
    @NotNull(message = "A CLASSE do PERSONAGEM é OBRIGATÓRIA.")
    private Classe classe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arma_principal_id")
    private Arma armaPrincipal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arma_secundaria_id")
    private Arma armaSecundaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armadura_id")
    private Armadura armadura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
