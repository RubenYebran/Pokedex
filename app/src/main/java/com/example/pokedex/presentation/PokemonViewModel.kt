package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokedex.core.Result
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers

class PokemonViewModel(private val repo: PokemonRepository) : ViewModel() {

    fun fetchPokemons() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.getPokemons()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}