package com.practice.pokedex.data.remote.responses

import com.practice.pokedex.data.model.Pokemon

data class PokemonModelData(
    val results: List<Pokemon>
) : java.io.Serializable
