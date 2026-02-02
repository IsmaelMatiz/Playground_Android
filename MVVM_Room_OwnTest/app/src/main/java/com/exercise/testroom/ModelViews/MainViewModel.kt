package com.exercise.testroom.ModelViews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.testroom.data.source.db.Bussines
import com.exercise.testroom.data.source.db.User
import com.exercise.testroom.data.source.db.UserWithBusinesses
import com.exercise.testroom.data.source.repos.UsersRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel(val repository: UsersRepository) : ViewModel()  {
    private val _fullName = mutableStateOf("")
    val fullName: State<String> = _fullName

    private val _age = mutableStateOf("")
    val age: State<String> = _age

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _businessName = mutableStateOf("")
    val businessName: State<String> = _businessName

    private val _businessImage = mutableStateOf("")
    val businessImage: State<String> = _businessImage

    private val _usersWithBusinesses = mutableStateOf(listOf<UserWithBusinesses>())
    val usersWithBusinesses: State<List<UserWithBusinesses>> = _usersWithBusinesses

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    fun onFullNameChange(newValue: String) {
        _fullName.value = newValue
    }

    fun onAgeChange(newValue: String) {
        if (newValue.toIntOrNull() == null){
            viewModelScope.launch {
                _errorMessage.emit("Edad debe ser un numero")
            }
            return
        }
        _age.value = newValue
    }

    fun onEmailChange(newValue: String) {
        _email.value = newValue
    }

    fun onBusinessNameChange(newValue: String) {
        _businessName.value = newValue
    }

    fun onBusinessImageChange(newValue: String) {
        _businessImage.value = newValue
    }

    init {
        getAllUsers()
    }

    private fun getAllUsers(){
        viewModelScope.launch {
            _usersWithBusinesses.value = repository.getAllUsersWithBusinesses()
        }
    }

    fun createUser() {
        if (_fullName.value.isNotEmpty() && _age.value.isNotEmpty() && _email.value.isNotEmpty()) {
            viewModelScope.launch { // <--- Todo dentro de un mismo launch
                if (_businessName.value.isNotEmpty() && _businessImage.value.isNotEmpty()) {
                    val user = User(fullName = _fullName.value, age = _age.value.toInt(), email = _email.value)
                    val business = Bussines(name = _businessName.value, image = _businessImage.value, userId = 0)

                    addUserWithBusiness(user, business) // Ahora se espera a que termine
                } else {
                    val user = User(fullName = _fullName.value, age = _age.value.toInt(), email = _email.value)
                    addUser(user)
                }

                getAllUsers()
            }
        }
    }

    private suspend fun addUserWithBusiness(user: User, business: Bussines)
    {
            repository.insertUserWithBusiness(user, business)
    }

    private suspend fun addUser(user: User)
    {
        repository.insertUser(user)
    }

    fun deleteUser(userId: Int){
        viewModelScope.launch {
            repository.deleteUser(userId)
        }

        getAllUsers()
    }
}

