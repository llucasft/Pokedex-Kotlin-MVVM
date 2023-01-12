package com.practice.pokedex.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.practice.pokedex.databinding.FragmentSearchPokemonBinding
import com.practice.pokedex.ui.base.BaseFragment

class PokemonSearchFragment : BaseFragment<FragmentSearchPokemonBinding, SearchPokemonViewModel>() {

    override val viewModel: SearchPokemonViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchPokemonBinding =
        FragmentSearchPokemonBinding.inflate(inflater, container, false)
}