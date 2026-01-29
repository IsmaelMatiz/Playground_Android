package com.exercise.testroom.ModelViews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.testroom.data.source.db.Bussines
import com.exercise.testroom.data.source.db.User
import com.exercise.testroom.data.source.db.UserWithBusinesses
import com.exercise.testroom.data.source.repos.UsersRepository
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

    init {
        getAllUsers()
    }

    private fun getAllUsers(){
        viewModelScope.launch {
            _usersWithBusinesses.value = repository.getAllUsersWithBusinesses()
        }
    }

    fun createUser() {
        if (_fullName.value.isNotEmpty() && _age.value.isNotEmpty()
            && _email.value.isNotEmpty())
        {
            if (_businessName.value.isNotEmpty() && _businessImage.value.isNotEmpty())
            {
                val user = User( fullName = _fullName.value,
                    age = _age.value.toInt(), email = _email.value )
                val business = Bussines( name = _businessName.value, image = _businessImage.value,
                    userId = 0  // se setea en el repo
                )

                addUserWithBusiness(user, business)
            }

            val user = User( fullName = _fullName.value,
                age = _age.value.toInt(), email = _email.value )

            addUser(user)
        } else{
            // Handle invalid input
        }
    }

    private fun addUserWithBusiness(user: User, business: Bussines)
    {
        viewModelScope.launch {
            repository.insertUserWithBusiness(user, business)
        }
    }

    private fun addUser(user: User)
    {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun deleteUser(userId: Int){
        viewModelScope.launch {
            repository.deleteUser(userId)
        }
    }
}

