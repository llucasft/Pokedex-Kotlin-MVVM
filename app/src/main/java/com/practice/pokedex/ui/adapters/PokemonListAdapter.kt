package com.practice.pokedex.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.pokedex.data.PokemonListItem
import com.practice.pokedex.data.remote.responses.Result
import com.practice.pokedex.databinding.PokemonItemBinding

class PokemonListAdapter(
    private var pokemonsList: List<Result>
) : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    inner class PokemonListViewHolder(val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: PokemonListItem) {
            val ivPokemon = binding.pokemonImage
            val tvName = binding.tvPokemonName
            val tvNumber = binding.tvPokemonIdNumber

            item.let {
                Glide.with(itemView.context)
                    .load(it.imageUrl)
                    .into(ivPokemon)
                tvNumber.text = "N° ${it.formattedNumber}"
                tvName.text = item.formattedName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val number =
            if (pokemonsList[position].url.endsWith("/")) {
                pokemonsList[position].url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                pokemonsList[position].url.takeLastWhile { it.isDigit() }
            }
        val item = PokemonListItem(
            number = number.toInt(),
            name = pokemonsList[position].name
        )
        holder.bindView(item)
    }

    override fun getItemCount(): Int = pokemonsList.size
}