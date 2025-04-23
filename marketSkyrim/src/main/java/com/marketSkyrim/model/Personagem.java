package com.marketSkyrim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@Schema(description = "Entidade que representa um personagem do jogo")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do personagem", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Nome do personagem", example = "Alduin")
    private String nome;

    @Min(1)
    @Max(99)
    @Schema(description = "Nível do personagem", example = "30")
    private int nivel;

    @Min(0)
    @Schema(description = "Moedas do personagem", example = "1000")
    private int moeda;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Classe do personagem", example = "MAGO")
    private Classe classe;
}
