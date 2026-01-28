package com.exercise.mytaskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Long = 1, // 1 = low, 2 = medium, 3 = high
    val isCompleted: Boolean = false,
    val createdAt: Long = Date().time,
){
    fun getPriorityColor(): Long {
        return when (priority) {
            3L -> 0xFFFF5252 // red - max priority
            2L -> 0xFFFFB74D // Orange - medium priority
            else -> 0xFF4CAF50 // Green - low priority
        }
    }

    fun getPriorityText(): String {
        return when (priority) {
            3L -> "High"
            2L -> "Medium"
            else -> "Low"
        }
    }
}
