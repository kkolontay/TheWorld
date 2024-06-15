package com.kkolontay.theworld.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow

object Flows {
    var counter = 0

    suspend  fun refresh() {
        counter = 0
        timer
    }

    val timer = flow {
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