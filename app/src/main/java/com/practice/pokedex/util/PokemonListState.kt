package com.practice.pokedex.util

import com.practice.pokedex.data.model.PokemonsListResponse

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonsList: PokemonsListResponse = PokemonsListResponse(results = emptyList(), count = 0),
    val error: String = ""
)
