package com.example.pokedex.data.remote

import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.repository.WebService

class PokemonDataSource(private val webService: WebService) {
    suspend fun getPokemons(): PokemonList = webService.getPokemons()
}