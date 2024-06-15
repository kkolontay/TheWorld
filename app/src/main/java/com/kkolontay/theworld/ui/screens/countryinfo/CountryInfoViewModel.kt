package com.kkolontay.theworld.ui.screens.countryinfo

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.model.Country
import com.kkolontay.theworld.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryInfoViewModel(private val repository: CountryRepository): ViewModel() {
    private val _uiState: MutableStateFlow<CountryInfoState> = MutableStateFlow(CountryInfoState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchCountries().collect {
                _uiState.value = it
            }
        }
    }

    companion object {
        fun provideFactory(repository: CountryRepository,
                           owner: SavedStateRegistryOwner,
                           defaultArgs: Bundle? = null): AbstractSavedStateViewModelFactory = object: AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return CountryInfoViewModel(repository) as T
            }
        }
    }

   fun fetchCountry(name: String): Country {
       return repository.getCountry(name)
    }
    fun updateCountryList() {
        viewModelScope.launch {
            repository.fetchCountries().collect {
                _uiState.value = it
            }
        }
    }
}
