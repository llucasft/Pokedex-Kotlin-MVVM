package com.practice.pokedex.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.pokedex.data.remote.responses.Result
import com.practice.pokedex.databinding.FragmentListPokemonsBinding
import com.practice.pokedex.ui.adapters.PokemonListAdapter
import com.practice.pokedex.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonsListFragment : BaseFragment<FragmentListPokemonsBinding, PokemonListViewModel>() {

    override val viewModel: PokemonListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectObserver()
    }

    //        viewModel.list.observe(viewLifecycleOwner, Observer {
//
//        })
    private fun collectObserver() = lifecycleScope.launch {
        viewModel.list.observe(viewLifecycleOwner, Observer { state ->
            if (state.isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
            if (state.pokemonsList.results.isNotEmpty()) {
                setupRecyclerView(state.pokemonsList.results)
            }
//            when (resource) {
//                is Resource.Success -> {
//                    resource.data?.let { values ->
//                        binding.progressCircular.visibility = View.GONE
//                        pokemonAdapter.pokemons = values.data.results.toList()
//                    }
//                }
//                is Resource.Error -> {
//                    binding.progressCircular.visibility = View.GONE
//                    resource.message?.let { message ->
//                        Timber.tag("ListPokemonsFragment").e("Error -> $message")
//                    }
//                }
//                is Resource.Loading -> {
//                    binding.progressCircular.visibility = View.VISIBLE
//                }
//                else -> {}
//            }
        })
    }

    private fun setupRecyclerView(pokemonList: List<Result>) = with(binding) {
        val adapter = PokemonListAdapter(pokemonList)
        binding.rvPokemons.adapter = adapter
        binding.rvPokemons.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListPokemonsBinding =
        FragmentListPokemonsBinding.inflate(inflater, container, false)
}