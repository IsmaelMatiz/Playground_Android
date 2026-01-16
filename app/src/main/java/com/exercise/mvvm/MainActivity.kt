package com.exercise.mvvm

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.exercise.mvvm.data.api.RetrofitInstance
import com.exercise.mvvm.data.model.WeatherResponse
import com.exercise.mvvm.data.repository.WeatherRepository
import com.exercise.mvvm.data.repository.WeatherRepositoryFactory
import com.exercise.mvvm.databinding.ActivityMainBinding
import com.exercise.mvvm.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: WeatherViewModel by viewModels{
        WeatherRepositoryFactory(
            WeatherRepository(RetrofitInstance.apiService)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.weatherData.observe(this) { weatherResponse ->
            weatherResponse?.let {
                updateWeatherUI(it)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            if(errorMessage.isNotEmpty()){
                binding.tvError.text = errorMessage
                binding.tvError.visibility = View.VISIBLE
                binding.weatherCard.visibility = View.GONE
            }else{
                binding.tvError.visibility = View.GONE
            }

        }
    }

    private fun updateWeatherUI(weatherResponse: WeatherResponse) {
        binding.tvLocation.text = weatherResponse.location.name
        binding.tvTemperature.text = "${weatherResponse.current.temp_c}°C"
        binding.tvCondition.text = weatherResponse.current.condition.text
        binding.tvFeelsLike.text = "${weatherResponse.current.feelslike_c}°C"
        binding.tvHumidity.text = "${weatherResponse.current.humidity}%"
        binding.tvWind.text = "${weatherResponse.current.wind_kph} km/h"

        binding.weatherCard.visibility = View.VISIBLE

    }

    private fun initViews() {
        binding.btnSearch.setOnClickListener {
            val location = binding.etSearch.text.toString().trim()
            if (location.isNotEmpty()) {
                hideKeyboard()
                viewModel.fetchWeatherData(location)
            }else{
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideKeyboard(){
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

    }
}