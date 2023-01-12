package com.practice.pokedex.repository

import com.practice.pokedex.data.remote.PokeApi
import com.practice.pokedex.data.remote.responses.Pokemon
import com.practice.pokedex.data.remote.responses.PokemonList
import com.practice.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error ocurred. ")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetails(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error ocurred. ")
        }
        return Resource.Success(response)
    }
}