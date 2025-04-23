package com.marketSkyrim.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.marketSkyrim.model.Classe;
import com.marketSkyrim.model.Item;
import com.marketSkyrim.model.Personagem;
import com.marketSkyrim.model.Raridade;
import com.marketSkyrim.model.Tipo;
import com.marketSkyrim.repository.ItemRepository;
import com.marketSkyrim.repository.PersonagemRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final PersonagemRepository personagemRepository;

    public DataSeeder(ItemRepository itemRepository, PersonagemRepository personagemRepository) {
        this.itemRepository = itemRepository;
        this.personagemRepository = personagemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Personagem p1 = new Personagem();
        p1.setNome("Dovahkiin");
        p1.setNivel(42);
        p1.setMoeda(1000);
        p1.setClasse(Classe.GUERREIRO);

        Personagem p2 = new Personagem();
        p2.setNome("Serana");
        p2.setNivel(35);
        p2.setMoeda(800);
        p2.setClasse(Classe.MAGO);

        personagemRepository.saveAll(List.of(p1, p2));

        Item espada = new Item();
        espada.setNome("Espada Épica");
        espada.setPreco(500);
        espada.setTipo(Tipo.ARMA);
        espada.setRaridade(Raridade.EPICO);
        espada.setDono(p1);

        Item poção = new Item();
        poção.setNome("Poção de Cura");
        poção.setPreco(50);
        poção.setTipo(Tipo.POCAO);
        poção.setRaridade(Raridade.COMUM);
        poção.setDono(p2);

        itemRepository.saveAll(List.of(espada, poção));


        System.out.println("Data seeded: " + espada.getNome() + ", " + poção.getNome());

        System.out.println("Dados carregados");
    }
}
