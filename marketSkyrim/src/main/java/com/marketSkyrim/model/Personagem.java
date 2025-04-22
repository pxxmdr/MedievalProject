package com.marketSkyrim.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Personagem {

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @Min(value = 1, message = "Nível deve ser maior que 0")
    @Max(value = 99, message = "Nível deve ser menor que 99")
    private int nivel;
    
    @Min(value = 0, message = "O saldo de Moeda não pode ser negativo")
    private int moeda;

    @NotNull(message = "Classe não pode ser nula")
    private Classe classe; 
}
