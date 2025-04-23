package com.marketSkyrim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marketSkyrim.model.Item;
import com.marketSkyrim.model.Personagem;
import com.marketSkyrim.model.Raridade;
import com.marketSkyrim.model.Tipo;
import com.marketSkyrim.repository.ItemRepository;
import com.marketSkyrim.repository.PersonagemRepository;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostMapping
    public ResponseEntity<Item> criarItem(@RequestBody Item item) {
        if (item.getDono() != null) {
            Long idDono = item.getDono().getId();
            Personagem dono = personagemRepository.findById(idDono)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dono não encontrado"));
            item.setDono(dono);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

    @GetMapping
    public ResponseEntity<Page<Item>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(itemRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody Item itemDetalhes) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setNome(itemDetalhes.getNome());
                    item.setPreco(itemDetalhes.getPreco());
                    item.setTipo(itemDetalhes.getTipo());
                    item.setRaridade(itemDetalhes.getRaridade());
                    if (itemDetalhes.getDono() != null) {
                        Long idDono = itemDetalhes.getDono().getId();
                        Personagem dono = personagemRepository.findById(idDono)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dono não encontrado"));
                        item.setDono(dono);
                    } else {
                        item.setDono(null);
                    }
                    return ResponseEntity.ok(itemRepository.save(item));
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado");
        }
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Filtros
    @GetMapping("/buscar/nome")
    public ResponseEntity<List<Item>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(itemRepository.findByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<Item>> buscarPorTipo(@RequestParam Tipo tipo) {
        return ResponseEntity.ok(itemRepository.findByTipo(tipo));
    }

    @GetMapping("/buscar/raridade")
    public ResponseEntity<List<Item>> buscarPorRaridade(@RequestParam Raridade raridade) {
        return ResponseEntity.ok(itemRepository.findByRaridade(raridade));
    }

    @GetMapping("/buscar/preco")
    public ResponseEntity<List<Item>> buscarPorFaixaDePreco(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return ResponseEntity.ok(itemRepository.findByPrecoBetween(min, max));
    }
}
