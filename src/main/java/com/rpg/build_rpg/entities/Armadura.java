package com.rpg.build_rpg.entities;

import com.rpg.build_rpg.entities.enums.TipoArmadura;
import jakarta.persistence.*;
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
    private String nome;

    @Column(nullable = false)
    private TipoArmadura tipo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer defesa;
}
