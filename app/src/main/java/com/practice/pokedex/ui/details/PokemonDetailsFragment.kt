package com.practice.pokedex.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.practice.pokedex.databinding.FragmentDetailsPokemonBinding
import com.practice.pokedex.ui.base.BaseFragment

class PokemonDetailsFragment :
    BaseFragment<FragmentDetailsPokemonBinding, PokemonDetailsViewModel>() {

    override val viewModel: PokemonDetailsViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsPokemonBinding =
        FragmentDetailsPokemonBinding.inflate(inflater, container, false)
}