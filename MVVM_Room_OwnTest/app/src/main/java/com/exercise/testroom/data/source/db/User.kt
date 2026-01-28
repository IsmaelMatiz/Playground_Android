package com.exercise.testroom.data.source.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val email: String,
    val bussines: Bussines
)
