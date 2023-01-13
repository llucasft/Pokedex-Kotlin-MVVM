package com.practice.pokedex.data.remote

import com.practice.pokedex.data.model.PokemonListEntry
import com.practice.pokedex.data.remote.responses.Pokemon
import com.practice.pokedex.data.remote.responses.PokemonList
import com.practice.pokedex.util.Constants.GET_POKEMONS_END_POINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

//    @GET("pokemon")
//    suspend fun getPokemonList(
//        @Query("limit") limit: Int,
//        @Query("offset") offset: Int
//    ): PokemonList

    @GET(GET_POKEMONS_END_POINT)
    suspend fun getPokemonList(): PokemonListEntry

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): Pokemon
}