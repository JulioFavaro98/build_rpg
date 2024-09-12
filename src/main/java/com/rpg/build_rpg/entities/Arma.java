package com.rpg.build_rpg.entities;

import com.rpg.build_rpg.entities.enums.TipoArma;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "armas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O NOME da ARMA é OBRIGATÓRIO.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O TIPO da ARMA é OBRIGATÓRIO.")
    private TipoArma tipo;

    @Column(nullable = false)
    @NotNull(message = "A DESCRIÇÃO da ARMA é OBRIGATÓRIA.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "O DANO da ARMA é OBRIGATÓRIO.")
    private Integer dano;

    @Column(nullable = false)
    @NotNull(message = "A NEGAÇÃO DE DANO da ARMA é OBRIGATÓRIO.")
    private Integer negacaoDano;

}
