package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("results")
    val results: List<Pokemon> = listOf()
)