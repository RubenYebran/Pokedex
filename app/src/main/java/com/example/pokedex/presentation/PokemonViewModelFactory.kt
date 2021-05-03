package com.example.pokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.repository.PokemonRepository

class PokemonViewModelFactory (private val repo: PokemonRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(PokemonRepository::class.java).newInstance(repo)
        }
}