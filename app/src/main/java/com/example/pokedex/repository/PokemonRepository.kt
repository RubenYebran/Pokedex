package com.example.pokedex.repository

import com.example.pokedex.data.model.PokemonList

interface PokemonRepository {
    suspend fun getPokemons(): PokemonList
}