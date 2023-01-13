package com.practice.pokedex.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.pokedex.databinding.FragmentListPokemonsBinding
import com.practice.pokedex.ui.adapters.PokemonListAdapter
import com.practice.pokedex.ui.base.BaseFragment
import com.practice.pokedex.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import timber.log.Timber

@AndroidEntryPoint
class PokemonsListFragment : BaseFragment<FragmentListPokemonsBinding, PokemonListViewModel>() {

    override val viewModel: PokemonListViewModel by viewModels()
    private val pokemonAdapter by lazy { PokemonListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectObserver()
    }
    //        viewModel.list.observe(viewLifecycleOwner, Observer {
//
//        })
    private fun collectObserver() = lifecycleScope.launch{
        viewModel.list.collect{ resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { values ->
                        binding.progressCircular.visibility = View.GONE
                        pokemonAdapter.pokemons = values.data.results.toList()
                    }
                }
                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    resource.message?.let { message ->
                        Timber.tag("ListPokemonsFragment").e("Error -> $message")
                    }
                }
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvPokemons.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListPokemonsBinding =
        FragmentListPokemonsBinding.inflate(inflater, container, false)
}