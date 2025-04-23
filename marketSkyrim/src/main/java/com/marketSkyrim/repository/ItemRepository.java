package com.marketSkyrim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketSkyrim.model.Item;
import com.marketSkyrim.model.Raridade;
import com.marketSkyrim.model.Tipo;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(Tipo tipo);
    List<Item> findByRaridade(Raridade raridade);
    List<Item> findByPrecoBetween(int min, int max);
}
