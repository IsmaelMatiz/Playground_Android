package com.exercise.mytaskmanager.repository

import androidx.lifecycle.LiveData
import com.exercise.mytaskmanager.data.Task
import com.exercise.mytaskmanager.data.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun getTaskById(id: Long): LiveData<Task> {
        return taskDao.getTaskById(id)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteTaskById(id: Int) {
        taskDao.deleteTaskById(id)
    }

}