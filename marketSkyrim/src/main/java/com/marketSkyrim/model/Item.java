package com.marketSkyrim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@Schema(description = "Entidade que representa um item do jogo")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do item", example = "1")
    private Long id;

    @NotNull(message = "O nome não pode ser nulo.")
    @Schema(description = "Nome do item", example = "Espada de Fogo")
    private String nome;

    @Min(value = 0, message = "O valor não pode ser negativo")
    @Schema(description = "Preço do item", example = "150")
    private int preco;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo do item", example = "ARMA")
    private Tipo tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Raridade do item", example = "ÉPICO")
    private Raridade raridade;

    @ManyToOne
    @Schema(description = "Dono do item [Um personagem]")
    private Personagem dono;
}
