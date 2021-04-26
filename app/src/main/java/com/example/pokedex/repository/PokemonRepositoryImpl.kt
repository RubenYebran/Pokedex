package com.example.pokedex.repository

import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.data.remote.PokemonDataSource

class PokemonRepositoryImpl(private val dataSource: PokemonDataSource): PokemonRepository {

    override suspend fun getPokemons(): PokemonList = dataSource.getPokemons()

}