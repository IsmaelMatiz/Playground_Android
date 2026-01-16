package com.exercise.mvvm.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.mvvm.viewmodel.WeatherViewModel

class WeatherRepositoryFactory(private val repository: WeatherRepository): ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }


}