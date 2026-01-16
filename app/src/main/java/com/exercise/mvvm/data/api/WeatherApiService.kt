package com.exercise.mvvm.data.api

import com.exercise.mvvm.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    // https://api.weatherapi.com/v1/current.json?key=5f8b4b5db50e48a19ce152522260701&q=Bogota&aqi=no

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") airQuality: String = "yes"
    ): Response<WeatherResponse>
}