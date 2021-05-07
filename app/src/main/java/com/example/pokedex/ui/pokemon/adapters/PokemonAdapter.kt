package com.example.pokedex.ui.pokemon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.databinding.PokemonItemBinding
import java.util.*
import kotlin.collections.ArrayList

class PokemonAdapter(private var listaPokemon: ArrayList<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>(), Filterable {

    var listaFiltrada = ArrayList<Pokemon>()

    init {
        listaFiltrada = listaPokemon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        //Info del xml de cada item. fragment_item
        return ViewHolder(
            layoutInflater.inflate(R.layout.pokemon_item, parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listaFiltrada[position])
    }

    override fun getItemCount(): Int = listaFiltrada.size

    inner class ViewHolder(view: View, private var context: Context) :
        RecyclerView.ViewHolder(view) {
        private val binding = PokemonItemBinding.bind(view)

        fun bind(pokemon: Pokemon) {
            var nombrePokemon = binding.tvNombrePokemon
            var urlPokemon = pokemon.url.split("/")

            var index = urlPokemon[urlPokemon.size - 2]


            nombrePokemon.text = pokemon.name.toUpperCase()

            Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index}.png")
                .centerCrop().into(binding.imgPokemon)
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val charSearch = constraint.toString()
                listaFiltrada = if (charSearch.isEmpty()) {
                    listaPokemon
                } else {
                    val resultList = ArrayList<Pokemon>()
                    listaPokemon.forEach {
                        if (it.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(it)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listaFiltrada
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listaFiltrada = results?.values as ArrayList<Pokemon>

                notifyDataSetChanged()
            }
        }
    }
}