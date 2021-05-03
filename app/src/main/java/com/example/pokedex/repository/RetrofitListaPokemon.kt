package com.example.pokedex.repository

import com.example.pokedex.application.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitListaPokemon {
    val webservice: WebService by lazy {
        Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)
    }
}