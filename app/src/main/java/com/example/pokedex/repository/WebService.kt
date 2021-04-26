package com.example.pokedex.repository

import com.example.pokedex.application.AppConstants
import com.example.pokedex.data.model.PokemonList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface WebService {
    @GET(AppConstants.PATH)
    suspend fun getPokemons(): PokemonList
}

object RetrofitListaPokemon {
    val webservice: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}