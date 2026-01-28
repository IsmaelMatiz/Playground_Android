package com.exercise.testroom.ModelViews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.testroom.data.source.db.UserWithBusinesses
import com.exercise.testroom.data.source.repos.UsersRepository
import kotlinx.coroutines.launch

class MainViewModel(repository: UsersRepository) : ViewModel()  {
    private val _usersWithBusinesses = mutableStateOf(listOf<UserWithBusinesses>())
    val usersWithBusinesses: State<List<UserWithBusinesses>> = _usersWithBusinesses


    init {
        viewModelScope.launch {
            _usersWithBusinesses.value = repository.getAllUsersWithBusinesses()
        }
    }
}