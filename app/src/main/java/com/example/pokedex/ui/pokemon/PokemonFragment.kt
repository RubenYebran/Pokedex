package com.example.pokedex.ui.pokemon

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.core.Resource
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.remote.PokemonDataSource
import com.example.pokedex.databinding.FragmentPokemonBinding
import com.example.pokedex.presentation.PokemonViewModel
import com.example.pokedex.presentation.PokemonViewModelFactory
import com.example.pokedex.repository.PokemonRepositoryImpl
import com.example.pokedex.repository.RetrofitListaPokemon
import com.example.pokedex.ui.pokemon.adapters.PokemonAdapter

class PokemonFragment : Fragment(R.layout.fragment_pokemon) {

    private lateinit var binding: FragmentPokemonBinding
    private lateinit var adapter: PokemonAdapter

    private val viewModel by viewModels<PokemonViewModel> {
        PokemonViewModelFactory(
            PokemonRepositoryImpl(
               PokemonDataSource(
                  RetrofitListaPokemon.webservice
               )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true) //Esto debe estar si o si para utilizar menus en un fragment.

        binding = FragmentPokemonBinding.bind(view)

        viewModel.fetchPokemons().observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    adapter = PokemonAdapter(it.data.results as ArrayList<Pokemon>)
                    binding.rvPokemon.adapter = adapter
                }
                is Resource.Failure -> {
                    Log.d("Error", "${it.exception}")
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter?.filter(newText) //Llamada a la función filter que se encuentra en la clase Adapter.
                return false
            }
        })
    }
}