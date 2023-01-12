package com.practice.pokedex.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.practice.pokedex.databinding.FragmentListPokemonsBinding
import com.practice.pokedex.ui.base.BaseFragment

class PokemonsListFragment: BaseFragment<FragmentListPokemonsBinding, PokemonListViewModel>() {

    override val viewModel: PokemonListViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListPokemonsBinding =
        FragmentListPokemonsBinding.inflate(inflater, container, false)
}