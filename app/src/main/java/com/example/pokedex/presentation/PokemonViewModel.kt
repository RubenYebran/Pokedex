package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.pokedex.core.Resource
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers

class PokemonViewModel(private val repo: PokemonRepository): ViewModel(){

    fun fetchPokemons() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(Resource.Success (repo.getPokemons()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}