package com.exercise.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.mvvm.data.model.WeatherResponse
import com.exercise.mvvm.data.repository.WeatherRepository
import com.exercise.mvvm.utils.Result
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchWeatherData("Bogota")
    }

    fun fetchWeatherData(location: String) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getCurrentWeather(location)) {
                is Result.Success -> {
                    _weatherData.value = result.data
                    _isLoading.value = false
                    _error.value = ""
                }
                is Result.Error -> {
                    _error.value = result.exception.message
                    _isLoading.value = false
                }
            }

        }
    }


}