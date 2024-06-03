package com.kkolontay.theworld.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

object Flows {
    var tapFlow: StateFlow<Int> = MutableStateFlow(0)
    var backFlow: StateFlow<Int> = MutableStateFlow(0)
    var counterFlow: StateFlow<Int> = MutableStateFlow(0)

init {
    GlobalScope.launch {
        counterFlow.onEach {
            delay(1_000_000)

        }.onStart {
                emit(0)
            }
            .transform {
                emit((counterFlow.value + 1))
            }
    }
}
    fun tap() {
        tapFlow
            .transform {
                emit(tapFlow.value + 1)
            }
    }
    fun tapBack() {
        backFlow.transform {
            emit(backFlow.value + 1)
        }
    }

    fun refresh() {
        tapFlow.transform {
            emit(0)
        }
        backFlow.transform {
            emit(0)
        }
        counterFlow.transform { emit(0) }
    }
}