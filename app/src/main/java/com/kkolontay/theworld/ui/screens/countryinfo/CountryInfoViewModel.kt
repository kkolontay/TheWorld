package com.kkolontay.theworld.ui.screens.countryinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.api.CountryInfoState
import com.kkolontay.theworld.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryInfoViewModel(val repository: CountryRepository): ViewModel() {
    private val _uiState: MutableStateFlow<CountryInfoState> = MutableStateFlow(CountryInfoState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchCountries().collect {
                _uiState.value = it
            }
        }
    }

    fun updateCountryList() {
        viewModelScope.launch {
            repository.updateListCountries()
        }
    }
}