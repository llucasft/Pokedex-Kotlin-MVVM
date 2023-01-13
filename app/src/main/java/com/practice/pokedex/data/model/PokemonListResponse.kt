package com.practice.pokedex.data.model

import com.practice.pokedex.data.remote.responses.Result

data class PokemonsListResponse(
    val count: Int,
    val results: List<Result>
)
