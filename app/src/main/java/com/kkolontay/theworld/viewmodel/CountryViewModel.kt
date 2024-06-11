package com.kkolontay.theworld.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkolontay.theworld.flow.Flows
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
   private val _timerState: MutableStateFlow<Int> = MutableStateFlow(0)
    val timerState = _timerState.asStateFlow()
    private val _tap: MutableStateFlow<Int> = MutableStateFlow(0)
    val tap = _tap.asStateFlow()
    private val _back: MutableStateFlow<Int> = MutableStateFlow(0)
    val back = _back.asStateFlow()
    val flows = Flows

    init {

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
        flows.counter = 0
        viewModelScope.launch {
            flows.timer.collect {
                _timerState.value = it
                println(it)
            }
        }
    }
}