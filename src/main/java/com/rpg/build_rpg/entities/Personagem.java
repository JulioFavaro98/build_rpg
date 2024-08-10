package com.rpg.build_rpg.entities;

import com.rpg.build_rpg.entities.enums.Classe;
import com.rpg.build_rpg.entities.enums.Raca;
import com.rpg.build_rpg.entities.enums.Sexo;
import jakarta.persistence.*;
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
    private String nome;

    @Column(nullable = false)
    private Sexo sexo;

    @Column(nullable = false)
    private Raca raca;

    @Column(nullable = false)
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

    private String religiao;
    private String pet;
}
