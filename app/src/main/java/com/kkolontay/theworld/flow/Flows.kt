package com.kkolontay.theworld.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow

public object Flows {
    private var tapFlow = MutableStateFlow(0)
    private var backFlow = MutableStateFlow(0)
    var counter = 0
    var tap = 0
    var back = 0

    public suspend fun tap() {
        tap += 1
        tapFlow.emit(tap)
    }
    public suspend fun tapBack() {
        back += 1
        backFlow.emit(back)
    }


    public suspend  fun refresh() {
        tap = 0
        tapFlow.emit(0)
        back = 0
        backFlow.emit(0)
        counter = 0
        timer
    }

    public fun fetchTapFlow(): StateFlow<Int> {
        return tapFlow
    }

    public fun fetchBackFlow(): StateFlow<Int> {
        return backFlow
    }

    public val timer = flow {
        counter = 0

        emit(counter)
        while (counter < 10_000) {
            delay(1_000)
            counter += 1
            emit(counter)
        }
    }
        .cancellable()
}