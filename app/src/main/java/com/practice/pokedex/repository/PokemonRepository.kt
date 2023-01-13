package com.practice.pokedex.repository

import com.practice.pokedex.data.remote.PokeApi
import com.practice.pokedex.data.remote.responses.PokemonResponse
import com.practice.pokedex.data.remote.responses.PokemonList
import com.practice.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int) = api.getPokemonList(50)

    suspend fun getPokemonDetails(pokemonName: String) = api.getPokemonDetails(pokemonName)
}