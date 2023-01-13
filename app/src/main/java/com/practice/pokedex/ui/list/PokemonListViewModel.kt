package com.practice.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.pokedex.data.remote.responses.PokemonModelResponse
import com.practice.pokedex.repository.PokemonRepository
import com.practice.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _list = MutableStateFlow<Resource<PokemonModelResponse>>(Resource.Loading())
    val list: StateFlow<Resource<PokemonModelResponse>> = _list

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        try {
            val response = repository.getPokemonList(20)
            _list.value = handleResponse(response)
        } catch (e: Exception) {
            Timber.e("An error ocurred")
        }
    }

    private fun handleResponse(response: Response<PokemonModelResponse>): Resource<PokemonModelResponse> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return Resource.Success(values)
            }
        }
        return Resource.Error(response.message())
    }
}