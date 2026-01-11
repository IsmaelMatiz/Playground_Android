package com.exercise.counterclickmvvm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    val counter = mutableStateOf(0)

    fun increaseCounter() {
        counter.value++
    }
}
