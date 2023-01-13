package com.practice.pokedex.data.remote

import com.practice.pokedex.data.remote.responses.PokemonResponse
import com.practice.pokedex.data.remote.responses.PokemonModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int
    ): Response<PokemonModelResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): PokemonResponse
}