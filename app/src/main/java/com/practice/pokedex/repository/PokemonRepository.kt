package com.practice.pokedex.repository

import com.practice.pokedex.data.model.PokemonListEntry
import com.practice.pokedex.data.remote.PokeApi
import com.practice.pokedex.data.remote.responses.Pokemon
import com.practice.pokedex.data.remote.responses.PokemonList
import com.practice.pokedex.util.Resource
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

//    suspend fun getPokemonList(limit: Int) = api.getPokemonList(50)
//
//    suspend fun getPokemonDetails(pokemonName: String) = api.getPokemonDetails(pokemonName)

    suspend fun getPokemons() : PokemonListEntry {
        return api.getPokemonList()
    }
//
//    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
//        val response = try {
//            api.getPokemonList(limit, offset)
//        } catch (e: Exception) {
//            return Resource.Error("An unknown error ocurred. ")
//        }
//        return Resource.Success(response)
//    }

    suspend fun getPokemonDetails(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetails(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error ocurred. ")
        }
        return Resource.Success(response)
    }
}