package com.rpg.build_rpg.entities;

import com.rpg.build_rpg.entities.enums.TipoArmadura;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "armaduras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Armadura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O NOME da ARMADURA é OBRIGATÓRIO.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O TIPO da ARMADURA é OBRIGATÓRIO.")
    private TipoArmadura tipo;

    @Column(nullable = false)
    @NotNull(message = "A DESCRIÇÃO da ARMADURA é OBRIGATÓRIA.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "A DEFESA da ARMADURA é OBRIGATÓRIA.")
    private Integer defesa;
}
