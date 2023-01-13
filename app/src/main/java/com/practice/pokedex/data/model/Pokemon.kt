package com.practice.pokedex.data.model

import com.practice.pokedex.data.remote.responses.Type

data class Pokemon(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val types: List<Type>
)