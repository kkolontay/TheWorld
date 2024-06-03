package com.kkolontay.theworld.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<WorldResponse> = MutableStateFlow(WorldResponse.Loading())
    val uiState = _uiState.asStateFlow()
    private val apiService = ApiBuilder.apiService

    fun fetchCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.fetchCountryList()
                _uiState.value = WorldResponse.Success(response)
                Log.i("Country", response.toString())
            } catch (e: Exception) {
                Log.i("TAG", e.message.toString())
                Log.i("TAG", e.localizedMessage.toString())
                _uiState.value = WorldResponse.ErrorResponse()
            }
        }
    }
}