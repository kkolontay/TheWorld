package com.kkolontay.theworld.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.api.ApiBuilder
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.flow.Flows
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
   private val _timerState: MutableStateFlow<Int> = MutableStateFlow(0)
    val timerState = _timerState.asStateFlow()
    private val _tap: MutableStateFlow<Int> = MutableStateFlow(0)
    val tap = _tap.asStateFlow()
    private val _back: MutableStateFlow<Int> = MutableStateFlow(0)
    val back = _back.asStateFlow()
    val flows = Flows

    init {
        viewModelScope.launch {
            getCountryInfoFlow().catch {
                _uiState.value = WorldResponse.Error(it.localizedMessage)
            }
                .collect {
                    _uiState.value = it
                    flows.counter = 1_000_000
                }
        }
        viewModelScope.launch {

            flows.fetchBackFlow().collect {
                _back.value = it
                println(it)
            }

        }
        viewModelScope.launch {
            flows.timer.collect {
                _timerState.value = it
                println(it)
            }
        }
        viewModelScope.launch {

            flows.fetchTapFlow().collect {
                _tap.value = it
                println(it)
            }
        }

    }

    fun refresh() {
        _uiState.value = WorldResponse.Loading()
        flows.counter = 0
        viewModelScope.launch {
            flows.timer.collect {
                _timerState.value = it
                println(it)
            }
        }
        viewModelScope.launch {
            getCountryInfoFlow().catch {
                _uiState.value = WorldResponse.Error(it.localizedMessage)
            }
                .collect {
                    _uiState.value = it
                    flows.counter = 1_000_000
                }
        }
    }

    private fun getCountryInfoFlow(): Flow<WorldResponse> {
      return  flow {
            val response = apiService.getAllCountry()
          if (response.isSuccessful) {
              emit(WorldResponse.Success(response.body() ?: listOf()))
          } else {
              emit(WorldResponse.Error(response.errorBody().toString()))
          }
        }
    }
}