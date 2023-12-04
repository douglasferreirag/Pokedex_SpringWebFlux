package com.webflux.pokedex.controller;

import com.webflux.pokedex.model.Pokemon;
import com.webflux.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private PokemonRepository repository;
    public PokemonController(PokemonRepository repository) {

        this.repository = repository;

    }


    @GetMapping
    public Flux<Pokemon> getAllPokemons() {

        return repository.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {

        return repository.findById(id)
                .map(pokemon -> ResponseEntity.OK(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon){

            return repository.save(pokemon);


    }

    @PutMapping("{id}")

    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id,
                                                        @RequestBody Pokemon pokemon){

        return repository.findById(id).flatMap(existingPokemon -> {

                    existingPokemon.setNome(pokemon.getNome());
                    existingPokemon.setCategoria(pokemon.getCategoria());
                    existingPokemon.setHabilidades(pokemon.getHabilidades());
                    existingPokemon.setPeso(pokemon.getPeso());
                    return repository.save(existingPokemon);

        }).map(updatePokemon -> ResponseEntity.OK(updatePokemon))
        .DefaultEmpty(ResponseEntity.notFound().build());

    }


}
