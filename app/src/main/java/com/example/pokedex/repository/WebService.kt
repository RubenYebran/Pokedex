package com.example.pokedex.repository

import com.example.pokedex.application.AppConstants
import com.example.pokedex.data.model.PokemonList
import retrofit2.http.GET


interface WebService {
    @GET(AppConstants.PATH)
    suspend fun getPokemons(): PokemonList
}

