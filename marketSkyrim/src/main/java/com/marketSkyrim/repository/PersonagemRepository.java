package com.marketSkyrim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketSkyrim.model.Classe;
import com.marketSkyrim.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeIgnoreCase(String nome); // 
    List<Personagem> findByClasse(Classe classe);
}
