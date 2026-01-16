package com.exercise.mvvm.data.repository

import com.exercise.mvvm.data.api.WeatherApiService
import com.exercise.mvvm.data.model.WeatherResponse
import com.exercise.mvvm.utils.Result

class WeatherRepository(private val apiService: WeatherApiService) {

    suspend fun getCurrentWeather(location: String): Result<WeatherResponse> {
        return  try {
            val response = apiService.getCurrentWeather(
                apiKey = "5f8b4b5db50e48a19ce152522260701", location =  location)

            if (response.isSuccessful) {
                val weatherResponse = response.body()
                if (weatherResponse != null) {
                    Result.Success(weatherResponse)
                } else {
                    Result.Error(Exception("Empty response body"))
                }
            } else {
                Result.Error(Exception("API request failed with code ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}