package com.practice.pokedex.data.model

import com.practice.pokedex.data.remote.responses.Result

import com.google.gson.annotations.SerializedName

data class PokemonListEntry(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)

fun PokemonListEntry.toPokemonsListResponse(): PokemonsListResponse {
    return PokemonsListResponse(
        count = count,
        results = results
    )
}
