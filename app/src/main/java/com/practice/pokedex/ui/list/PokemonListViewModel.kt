package com.practice.pokedex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.pokedex.data.model.toPokemonsListResponse
import com.practice.pokedex.repository.PokemonRepository
import com.practice.pokedex.util.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _list = MutableLiveData<PokemonListState>()
    val list: LiveData<PokemonListState> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        try {
            _list.value = PokemonListState(true)
            val pokemonListEntry = repository.getPokemons()
            val pokemonsList = pokemonListEntry.toPokemonsListResponse()
            _list.value = PokemonListState(pokemonsList = pokemonsList)
        } catch (e: Exception) {
            _list.value = PokemonListState(error =  e.message.toString())
        }
//        safeFetch()
    }
//
//    private suspend fun safeFetch() {
//        try {
//            val response = repository.getPokemonList(20)
//            _list.value = handleResponse(response)
//        } catch (e: Exception) {
//            Timber.e("An error ocurred")
//        }
//    }

//    private fun handleResponse(response: Response<PokemonModelResponse>): Resource<PokemonModelResponse> {
//        if (response.isSuccessful) {
//            response.body()?.let { values ->
//                return Resource.Success(values)
//            }
//        }
//        return Resource.Error(response.message())
//    }
}