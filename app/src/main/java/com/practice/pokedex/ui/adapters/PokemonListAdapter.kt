package com.practice.pokedex.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.pokedex.data.model.Pokemon
import com.practice.pokedex.databinding.PokemonItemBinding

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    inner class PokemonListViewHolder(val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.height == newItem.height &&
                    oldItem.number == newItem.number &&
                    oldItem.name == newItem.name &&
                    oldItem.weight == newItem.weight &&
                    oldItem.types == newItem.types
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    var pokemons: List<Pokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

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
        val pokemon = pokemons[position]
        holder.binding.apply {
            tvPokemonIdNumber.text = pokemon.number.toString()
            tvPokemonName.text = pokemon.name
            tvType1.text = pokemon.types[0].toString()
            tvType2.text = pokemon.types[1].toString()
        }
    }

    override fun getItemCount(): Int = pokemons.size
}