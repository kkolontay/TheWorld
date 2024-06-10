package com.kkolontay.theworld.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.WorldResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<WorldResponse> = MutableStateFlow(WorldResponse.Loading)
    val uiState = _uiState.asStateFlow()
    private val apiService = ApiBuilder.apiService

    fun fetchCountryList() {
        _uiState.value = WorldResponse.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getAllCountries()
                if (response.isSuccessful) {
                    _uiState.value = WorldResponse.Success(response.body() ?: listOf())
                } else {
                    _uiState.value = WorldResponse.Error
                }
                Log.i("Country", response.toString())
            } catch (e: Exception) {
                Log.i("TAG", e.message.toString())
                Log.i("TAG", e.localizedMessage?.toString() ?: "Error")
                _uiState.value = WorldResponse.Error
            }
        }
    }
}