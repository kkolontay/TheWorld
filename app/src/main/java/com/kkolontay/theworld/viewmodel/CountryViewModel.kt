package com.kkolontay.theworld.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.ApiInterface
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<WorldResponse> = MutableStateFlow(WorldResponse.Loading())
    val uiState = _uiState.asStateFlow()
    private val apiService = ApiBuilder.apiService

    init {
        viewModelScope.launch {
            getCountryInfoFlow().catch {
                _uiState.value = WorldResponse.ErrorResponse()
            }
                .collect {
                    _uiState.value = it
                }
        }
    }

    private fun getCountryInfoFlow(): Flow<WorldResponse> {
      return  flow<WorldResponse> {
            var response = apiService.fetchCountryList()
            emit(WorldResponse.Success(response))
        }
    }
}