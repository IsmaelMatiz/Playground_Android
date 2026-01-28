package com.exercise.mytaskmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.exercise.mytaskmanager.repository.TaskRepository
import androidx.lifecycle.ViewModelProvider

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}